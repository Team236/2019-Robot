/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ManualPogoRoll extends Command {
  public ManualPogoRoll() {
    // requires(subsystem);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    // TODO: might want to add safety button that must be held
    Robot.elevator.pogoRoll(Robot.oi.controller.getRightY());
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.elevator.pogoRoll(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
