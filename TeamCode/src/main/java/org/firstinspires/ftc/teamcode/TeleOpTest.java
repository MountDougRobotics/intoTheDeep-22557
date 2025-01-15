package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.TeleOpDrive;

@TeleOp
public class TeleOpTest extends OpMode {

    TeleOpDrive drive;

    @Override
    public void init() {
        Config.hardwareMap = hardwareMap;
        drive = new TeleOpDrive();
    }

    @Override
    public void loop() {
        drive.drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, (gamepad1.left_trigger-gamepad1.right_stick_x));
    }
}
