package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drive.TeleOpDrive;

/**
 * TeleOP controls
 * -> Planar movement = Left Joystick
 * -> Rotational movement = Right Joystick X axis
 * -> Intake toggle = A
 * -> Arm rotation =
 * -> Slide movement =
 */

@TeleOp(name="Tele Op v1")
public class BasicTeleOp extends OpMode {

    TeleOpDrive drive;
    double intakePower = 0;
    double armPosition = 0;
    double slidePosition = 0;
    double direction = 0;
    double targetDirection = 0;
    double rotation = 0;

    @Override
    public void init() {
        Config.initialize(hardwareMap, telemetry);
        drive = new TeleOpDrive();

        Config.resetAngle();

    }

    @Override
    public void loop() {

        if(gamepad1.right_bumper){
            Config.resetAngle();
        }

        direction = Config.getAngle();

        if(gamepad1.right_stick_x != 0 || gamepad1.right_bumper){
            targetDirection = direction;
        }

        rotation = ((Config.getAngle() - targetDirection) / 40) + gamepad1.right_stick_x;

        rotation /= Math.max(1, Math.abs(rotation));

        drive.drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, rotation, direction);

        if(gamepad2.left_stick_y != 0){
            armPosition = Config.arm.getCurrentPosition() - (300.0 * gamepad2.left_stick_y);
        }

        if(gamepad2.right_stick_x != 0){
            slidePosition = Config.slide.getCurrentPosition() + (300.0 * gamepad2.right_stick_x);
        }

        if(!gamepad2.left_bumper){
            armPosition = Math.min(3400, armPosition);
            armPosition = Math.max(0, armPosition);

            if(2500 < armPosition) {
                slidePosition = Math.min(3870, slidePosition);
            } else {
                slidePosition = Math.min(2000, slidePosition);
            }

            slidePosition = Math.max(0, slidePosition); // slide 1420 arm 300

        }

        if(gamepad2.right_bumper){

            Config.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Config.slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Config.arm.setPower(1);
            Config.slide.setPower(1);
            Config.arm.setTargetPosition(0);
            Config.slide.setTargetPosition(0);

            Config.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Config.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            armPosition = 0;
            slidePosition = 0;

        }

        if(gamepad2.a) {
            armPosition = 300;
            slidePosition = 1470;
        } else if (gamepad2.b) {
            armPosition = 3820;
            slidePosition = 3870;
        }

        Config.arm.setTargetPosition((int) armPosition); // gamepad2.left_stick_y (int) armPosition
        Config.slide.setTargetPosition((int) slidePosition); // gamepad2.right_stick_y

        intakePower = (gamepad2.left_trigger - gamepad2.right_trigger);


        Config.intakeLeft.setPower(intakePower);
        Config.intakeRight.setPower(intakePower - 0.04);

        telemetry.addData("intakePower", intakePower);
        telemetry.addData("armPosition", armPosition);
        telemetry.addData("slidePosition", slidePosition);
        telemetry.addData("armEncoder", Config.arm.getCurrentPosition());
        telemetry.addData("direction", Config.getAngle());
        telemetry.addData("targetDirection", targetDirection);
        telemetry.addData("rotation", rotation);
        telemetry.addData("IMU YAW", Config.imu.getRobotYawPitchRollAngles().getYaw());
        telemetry.update();

        Config.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Config.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
