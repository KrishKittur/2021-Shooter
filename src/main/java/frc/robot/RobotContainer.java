package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.shooter.HoodSubsystem;

// Robot container
public class RobotContainer {

  // Variables
  public final HoodSubsystem hoodSubsystem = new HoodSubsystem();

  // In the constructor configure the button bindings
  public RobotContainer() {
    configureButtonBindings();
  }

  // Method to configure the button bindings
  private void configureButtonBindings() {}

  // Method to get the selected autonomous command
  public Command getAutonomousCommand() {
    return null;
  }
}
