package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.TeleOpDrive;

@Disabled
@Autonomous(name="Snacking Autonomous Opmode")
public class SnackingAuto extends LinearOpMode {
    TeleOpDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new TeleOpDrive();
        waitForStart();

        // push all 3 in
        drive.drive(0.8, 0, 0);
        sleep(200);
        for (int i = 0; i<3; i++) {
            drive.drive(0.8, 0, 0);
            sleep(800);
            drive.drive(0, -0.5, 0);
            sleep(300);
            drive.drive(-0.8, 0, 0);
            sleep(800);
        }
    }
}
