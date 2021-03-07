package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.spindexer.SpindexerSubsystem;

// Robot container
public class RobotContainer {

  // Variables
  SpindexerSubsystem spindexerSubsystem = new SpindexerSubsystem();
  XboxController controller = new XboxController(0);

  // In the constructor configure the button bindings and default commands
  public RobotContainer() {
    configureButtonBindings();
    spindexerSubsystem.setDefaultCommand(
      new RunCommand(() -> spindexerSubsystem.setVoltage(0.0), spindexerSubsystem)
    );
  }

  // Method to configure the button bindings
  private void configureButtonBindings() {
    new JoystickButton(controller, Button.kA.value).whenPressed(
      new RunCommand(() -> spindexerSubsystem.setVoltage(3), spindexerSubsystem)
    );
    new JoystickButton(controller, Button.kB.value).whenPressed(
      new RunCommand(() -> spindexerSubsystem.setVoltage(-3), spindexerSubsystem)
    );
  }

  // Method to get the selected autonomous command
  public Command getAutonomousCommand() {
    return null;
  }
}
