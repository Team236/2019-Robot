/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import lib.pid.PID;
import lib.pid.PIDParameters;

public class ElevatorToHeight extends Command {

  private PID pid;
  private double height;
  private double margin;
  private double heightError;

  public static PIDParameters UP_PARAMS, DOWN_PARAMS;

  public ElevatorToHeight(double _height, double _margin, PIDParameters _pid) {
    requires(Robot.elevator);
    this.height = _height;
    this.margin = _margin;

    if (height > Robot.elevator.getHeight()) {
      _pid = UP_PARAMS;
    } else if (height <= Robot.elevator.getHeight()) {
      _pid = DOWN_PARAMS;
    }

    pid = new PID(Robot.elevator, Robot.elevator, _pid);
  }

  @Override
  protected void initialize() {
    pid.setSetpoint(height);

    Robot.elevator.resetEncoder();
    
    pid.enable();
    
    pid.update();
  }

  @Override
  protected void execute() {
    heightError = pid.getError();
    SmartDashboard.putNumber("height error", heightError);
  }

  @Override
  protected boolean isFinished() {
    return (Math.abs(heightError) < margin);
  }

  @Override
  protected void end() {
    pid.disable();
    Robot.elevator.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
