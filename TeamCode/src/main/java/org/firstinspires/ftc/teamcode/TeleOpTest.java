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

    @Override
    public void init() {
        Config.initialize(hardwareMap, telemetry);
        drive = new TeleOpDrive();


    }

    @Override
    public void loop() {
        drive.drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);

        armPosition += (10.0 * gamepad2.left_stick_y);
        armPosition = Math.min((1440.0 * 6.0 * 0.6), armPosition);
        armPosition = Math.max(0, armPosition);

        Config.arm.setTargetPosition((int) armPosition); // gamepad2.left_stick_y

        slidePosition += (10.0 * gamepad2.right_stick_y);
        slidePosition = Math.min((1440.0 * 10), slidePosition);
        slidePosition = Math.max(0, slidePosition);

        Config.slide.setTargetPosition((int) slidePosition); // gamepad2.right_stick_y

        intakePower = (gamepad2.left_trigger - gamepad2.right_trigger);

        Config.intakeRight.setPower(intakePower);
        Config.intakeLeft.setPower(intakePower);

        Config.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Config.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
