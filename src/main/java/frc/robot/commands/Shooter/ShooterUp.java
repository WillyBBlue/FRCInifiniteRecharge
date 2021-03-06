/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.TestingDashboard;
import frc.robot.subsystems.Shooter;

public class ShooterUp extends CommandBase {
  /**
   * Creates a new ShooterUp.
   */
  Shooter m_shooter;
  DoubleSolenoid m_piston;
  boolean m_finished = false;
  boolean isDown = false;

  public ShooterUp() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Shooter.getInstance());
    m_shooter = Shooter.getInstance();
    m_piston = m_shooter.getPiston();
  }

  public static void registerWithTestingDashboard() {
    Shooter shooter = Shooter.getInstance();
    ShooterUp cmd = new ShooterUp();
    TestingDashboard.getInstance().registerCommand(shooter, "Basic", cmd);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (m_piston.get() == DoubleSolenoid.Value.kReverse) {
      isDown = true;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (isDown) {
      m_shooter.raiseShooter();
    }

    if (m_piston.get() == DoubleSolenoid.Value.kForward) {
      m_finished = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_finished;
  }
}
