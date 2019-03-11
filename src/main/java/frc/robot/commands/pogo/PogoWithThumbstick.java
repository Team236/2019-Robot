/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.pogo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class PogoWithThumbstick extends Command {
  public PogoWithThumbstick() {
    // requires(Robot.pogo);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    double speed = Robot.oi.controller.getRightY();
    Robot.pogo.setPogoSpeed(-speed, -speed);

    double rollSpeed = Robot.oi.controller.getRightX();
    Robot.pogo.setRollSpeed(rollSpeed);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.pogo.stopPogo();
    Robot.pogo.stopRoll();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
