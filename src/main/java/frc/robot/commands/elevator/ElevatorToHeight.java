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
import frc.robot.RobotMap;
import lib.pid.PID;
import lib.pid.PIDParameters;

public class ElevatorToHeight extends Command {

  private PID pid;
  private double height;
  private double margin;
  private double heightError;

  public ElevatorToHeight(double _height, double _margin, PIDParameters _upPid, PIDParameters _downPid) {
    requires(Robot.elevator);
    this.height = _height;
    this.margin = _margin;

    if (height >= Robot.elevator.getHeight()) {
      pid = new PID(Robot.elevator, Robot.elevator, _upPid);
    } else if (height < Robot.elevator.getHeight()) {
      pid = new PID(Robot.elevator, Robot.elevator, _downPid);
    }
  }

  @Override
  protected void initialize() {
    // TODO: test logic below to prevent going up w/ cargo extended
    // if (Robot.cargo.isExtended()) {
      // Robot.elevator.stop();
    // } else {
      pid.setSetpoint(height);

      Robot.elevator.resetAtBottom();

      pid.enable();

      pid.update();
    // System.out.println("elevatorToHeight starting");

    // }
  }

  @Override
  protected void execute() {
    heightError = pid.getError();
    SmartDashboard.putNumber("height error", heightError);
    // System.out.println("elevatorToHeight execute");
  }

  @Override
  protected boolean isFinished() {
    if (Math.abs(heightError) < margin) {
      return true;
    } else if ((Robot.elevator.atTop() || Robot.elevator.getHeight() >= RobotMap.ElevatorMap.TOP_HEIGHT)
        && height > Robot.elevator.getHeight()) {
      return true;
    } else if (Robot.elevator.atBottom() && height < Robot.elevator.getHeight()) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  protected void end() {
    pid.disable();
    Robot.elevator.stop();
    Robot.elevator.resetAtBottom();
    // System.out.println("elevatorToHeight ending");
  }

  @Override
  protected void interrupted() {
    end();
  }
}
