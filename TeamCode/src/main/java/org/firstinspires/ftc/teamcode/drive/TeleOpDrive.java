package org.firstinspires.ftc.teamcode.drive;

import static org.firstinspires.ftc.teamcode.Config.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Config;


public class TeleOpDrive {
    DcMotorEx[] motors = new DcMotorEx[4];

    public TeleOpDrive() {
        motors[0] = hardwareMap.get(DcMotorEx.class, Config.frontLeft);
        motors[1] = hardwareMap.get(DcMotorEx.class, Config.frontRight);
        motors[2] = hardwareMap.get(DcMotorEx.class, Config.rearLeft);
        motors[3] = hardwareMap.get(DcMotorEx.class, Config.rearRight);

        motors[0].setDirection(DcMotorSimple.Direction.REVERSE);
        motors[2].setDirection(DcMotorSimple.Direction.REVERSE);

    }

    public void drive(double x, double y, double r) {
        double theta = Math.atan2(y, x);
        double pow = Math.hypot(x, y)/(Math.hypot(1, 1));

        double xCom = pow * Math.cos(theta - Math.PI/4);
        double yCom = pow * Math.sin(theta - Math.PI/4);
        double max = Math.max(xCom, yCom);

        motors[0].setPower(pow * (xCom/max) + r);
        motors[1].setPower(pow * (yCom/max) - r);
        motors[2].setPower(pow * (yCom/max) + r);
        motors[3].setPower(pow * (xCom/max) - r);

    }
}
