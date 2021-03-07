package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.shooter.ToAngle;
import frc.robot.subsystems.shooter.TurretSubsystem;

// Robot container
public class RobotContainer {

  // Variables
  private final TurretSubsystem turretSubsystem = new TurretSubsystem();
  private final XboxController controller = new XboxController(0);

  // In the constructor configure the button bindings and set the default commands
  public RobotContainer() {
    configureButtonBindings();
  }

  // Method to configure the button bindings
  private void configureButtonBindings() {
    new JoystickButton(controller, XboxController.Button.kA.value).whenPressed(
      new ToAngle(turretSubsystem, 151)
    );
    new JoystickButton(controller, XboxController.Button.kB.value).whenPressed(
      new ToAngle(turretSubsystem, 90)
    );
    new JoystickButton(controller, XboxController.Button.kX.value).whenPressed(
      new ToAngle(turretSubsystem, -90)
    );
    new JoystickButton(controller, XboxController.Button.kY.value).whenPressed(
      new ToAngle(turretSubsystem, 0)
    );
  }

  // Method to get the selected autonomous command
  public Command getAutonomousCommand() {
    return null;
  }
}
