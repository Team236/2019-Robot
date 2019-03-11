/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetElevatorSpeed extends Command {
  private double speed;
  public SetElevatorSpeed(double _speed) {
    this.speed = _speed;

    requires(Robot.elevator);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    // might need to add elevator bottom limit logic here
    Robot.elevator.setSpeed(speed);
  }

  @Override
  protected boolean isFinished() {
    return Robot.elevator.atBottom() || Robot.elevator.getHeight() < 4;
  }

  @Override
  protected void end() {
    Robot.elevator.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
