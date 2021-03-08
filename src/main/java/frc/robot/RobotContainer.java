package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.spindexer.ToAngle;
import frc.robot.subsystems.spindexer.SpindexerSubsystem;

// Robot container
public class RobotContainer {

  // Variables
  SpindexerSubsystem spindexerSubsystem = new SpindexerSubsystem();
  public XboxController controller = new XboxController(0);

  // In the constructor configure the button bindings and default commands
  public RobotContainer() {
    configureButtonBindings();
  }

  // Method to configure the button bindings
  private void configureButtonBindings() {
    new JoystickButton(controller, Button.kA.value).whileHeld(
      new ToAngle(spindexerSubsystem)
    );
  }

  
  // Method to get the selected autonomous command
  public Command getAutonomousCommand() {
    return null;
  }
}
