package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
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
    float intakePower = 0;

    @Override
    public void init() {
        Config.initialize(hardwareMap);
        drive = new TeleOpDrive();

        //Config.imu.initialize(Config.imuParam);

    }

    @Override
    public void loop() {
        drive.drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);

        intakePower = (gamepad2.left_trigger - gamepad2.right_trigger);

        Config.intakeRight.setPower(intakePower);
        Config.intakeLeft.setPower(intakePower);

        Config.slide.setPower(gamepad2.right_stick_y);
        Config.arm.setPower(gamepad2.left_stick_y);

    }
}
