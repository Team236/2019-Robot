/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.elevator.StopElevator;
import lib.pid.PIDOutput;
import lib.pid.PIDSource;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem implements PIDSource, PIDOutput {

  public TalonSRX leftMasterElevator;
  public VictorSPX rightSlave;

  private DigitalInput topLimit, bottomLimit;

  public Elevator() {
    leftMasterElevator = new TalonSRX(RobotMap.ElevatorMap.ID_T_LEFT_MASTER);
    rightSlave = new VictorSPX(RobotMap.ElevatorMap.ID_V_RIGHT_SLAVE);

    rightSlave.follow(leftMasterElevator);

    leftMasterElevator.setSensorPhase(false);

    topLimit = new DigitalInput(RobotMap.ElevatorMap.DIO_TOP_LIMIT);
    bottomLimit = new DigitalInput(RobotMap.ElevatorMap.DIO_BOTTOM_LIMIT);
  }

  public boolean atTop() {
    return topLimit.get();
  }

  public boolean atBottom() {
    return bottomLimit.get();
  }

  public int getEncoder() {
    return leftMasterElevator.getSelectedSensorPosition();
  }

  public double getHeight() {
    return getEncoder() * RobotMap.ElevatorMap.DISTANCE_PER_PULSE;
  }

  public void resetEncoder() {
    leftMasterElevator.setSelectedSensorPosition(0);
  }

  public void resetAtBottom() {
    if (atBottom()) {
      resetEncoder();
    }
  }

  public void stop() {
    leftMasterElevator.set(ControlMode.PercentOutput, 0);
  }

  public void setSpeed(double speed) {
    // leftMaster.set(ControlMode.PercentOutput, speed);
    // || getHeight() >= RobotMap.ElevatorMap.TOP_HEIGHT 
    if ((atTop() || getHeight() >= RobotMap.ElevatorMap.TOP_HEIGHT || Robot.cargo.isExtended()) && speed > 0) {
      speed = 0;
    } else if (atBottom() && speed < 0) {
      speed = 0;
    }
    leftMasterElevator.set(ControlMode.PercentOutput, speed);
  }

  public void pidSet(double speed) {
    leftMasterElevator.set(ControlMode.PercentOutput, speed);
  }

  public double pidGet() {
    return getHeight();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new StopElevator());
  }
}
