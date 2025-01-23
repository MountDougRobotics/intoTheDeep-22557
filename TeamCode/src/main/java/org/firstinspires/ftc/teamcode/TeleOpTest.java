package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

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
public class TeleOpTest extends OpMode {

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
        direction = Config.getAngle();

        if(gamepad1.right_stick_x != 0){
            targetDirection = direction;

            telemetry.addData("turning", "true");
        }

        rotation = ((Config.getAngle() - targetDirection) / 40) + gamepad1.right_stick_x;

        rotation /= Math.max(1, Math.abs(rotation));

        drive.drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, rotation, direction);

        if(gamepad2.left_stick_y != 0){
            armPosition = Config.arm.getCurrentPosition() + (300.0 * gamepad2.left_stick_y);
            //armPosition = Math.min((1440.0 * 6.0 * 0.6), armPosition);
            armPosition = Math.max(0, armPosition);
        }

        Config.arm.setTargetPosition((int) armPosition); // gamepad2.left_stick_y (int) armPosition

        if(gamepad2.right_stick_y != 0){
            slidePosition = Config.slide.getCurrentPosition() + (300.0 * gamepad2.right_stick_y);
            //slidePosition = Math.min((1440.0 * 10), slidePosition);
            //slidePosition = Math.max(0, slidePosition);
        }

        Config.slide.setTargetPosition((int) slidePosition); // gamepad2.right_stick_y

        intakePower = (gamepad2.left_trigger - gamepad2.right_trigger);


        Config.intakeLeft.setPower(intakePower);
        Config.intakeRight.setPower(intakePower - 0.04);

        telemetry.addData("intakePower", intakePower);
        telemetry.addData("slidePosition", slidePosition);
        telemetry.addData("armEncoder", Config.arm.getCurrentPosition());
        telemetry.addData("direction", Config.getAngle());
        telemetry.addData("targetDirection", targetDirection);
        telemetry.addData("rotation", rotation);
        telemetry.update();

        Config.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Config.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
