package frc.robot.subsystems.arm;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import frc.robot.RobotContainer;

public class ArmCommands {
    public static Command getCollectComand(double setPoint){
        return new FunctionalCommand(
                () -> RobotContainer.ARM.theWork(setPoint),
                () -> RobotContainer.ARM.theWork(setPoint),
                (interrupted) -> RobotContainer.ARM.stop(),
                ()-> false
        );

    }
}
