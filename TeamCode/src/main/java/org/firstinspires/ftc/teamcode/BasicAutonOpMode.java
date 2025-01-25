package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.TeleOpDrive;

@Autonomous(name="Going RIGHT Auto")
public class BasicAutonOpMode extends LinearOpMode {
    TeleOpDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {
        Config.initialize(hardwareMap, telemetry);
        drive = new TeleOpDrive();

        waitForStart();

        drive.drive(0.5, 0, 0);
        sleep(3000);
        drive.drive(0, 0 ,0);
    }
}
