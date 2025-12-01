package frc.robot.subsystems.arm;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import frc.robot.RobotContainer;

public class ArmCommands {
    public static Command getCollectComand(double targetAngle){
        return new FunctionalCommand(
                () -> RobotContainer.ARM.theWork(targetAngle),
                () -> RobotContainer.ARM.theWork(targetAngle),
                (interrupted) -> RobotContainer.ARM.stop(),
                ()-> false
        );
    }
}
