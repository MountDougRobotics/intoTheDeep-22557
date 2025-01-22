package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Config;


public class TeleOpDrive {
    DcMotorEx[] motors = new DcMotorEx[4];

    public TeleOpDrive() {
        motors[0] = Config.frontLeft;
        motors[1] = Config.frontRight;
        motors[2] = Config.rearLeft;
        motors[3] = Config.rearRight;
    }

    public void drive(double x, double y, double r, double direction) {
        double theta = (Math.atan2(y, x) - Math.toRadians(direction));
        double pow = Math.hypot(x, y)/(Math.hypot(1, 1));

        double xCom = pow * Math.cos(theta - Math.PI/4);
        double yCom = pow * Math.sin(theta - Math.PI/4);
        double max =Math.max(1, Math.abs(Math.max(Math.abs(xCom)+r, Math.abs(yCom)+r)));

        motors[0].setPower(xCom/max + r);
        motors[1].setPower(yCom/max - r);
        motors[2].setPower(yCom/max + r);
        motors[3].setPower(xCom/max - r);

        Config.telemery.addData("FL", motors[0].getPower());
        Config.telemery.addData("FR", motors[1].getPower());
        Config.telemery.addData("RL", motors[2].getPower());
        Config.telemery.addData("RR", motors[3].getPower());
        Config.telemery.addData("R", r);

        Config.telemery.update();

    }

    public void drive(double x, double y, double r){
        drive(x, y, r, 0);
    }
}
