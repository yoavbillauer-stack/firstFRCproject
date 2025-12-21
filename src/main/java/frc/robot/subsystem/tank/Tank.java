package frc.robot.subsystem.tank;

public class Tank {
    private void arcadeDrive() {
        double forward = TankConstants.getForward();
        double rotation = TankConstants.getRotation();

        double leftPower = forward + rotation;
        double rightPower = forward - rotation;

        leftPower = checkIfValFits(leftPower, -TankConstants.MAX_SPEED, TankConstants.MAX_SPEED);
        rightPower = checkIfValFits(rightPower, -TankConstants.MAX_SPEED, TankConstants.MAX_SPEED);

        TankConstants.LEFT_FRONT_MOTOR.set(leftPower);
        TankConstants.LEFT_REAR_MOTOR.set(leftPower);
        TankConstants.RIGHT_FRONT_MOTOR.set(rightPower);
        TankConstants.RIGHT_REAR_MOTOR.set(rightPower);
    }

    private double checkIfValFits(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}
