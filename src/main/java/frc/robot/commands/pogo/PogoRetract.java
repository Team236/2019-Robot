/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.pogo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class PogoRetract extends Command {
  public PogoRetract() {
    requires(Robot.pogo);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.pogo.setLeftPogoSpeed(RobotMap.PogoMap.LEFT_RETRACT_SPEED);
    Robot.pogo.setRightSpeed(RobotMap.PogoMap.RIGHT_RETRACT_SPEED);
  }

  @Override
  protected boolean isFinished() {
    return Robot.pogo.atTop();
  }

  @Override
  protected void end() {
    Robot.pogo.stopPogo();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
