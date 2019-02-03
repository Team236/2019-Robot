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
// import edu.wpi.first.wpilibj.PIDOutput;
// import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import lib.pid.PIDOutput;
import lib.pid.PIDSource;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem implements PIDSource, PIDOutput {
  public TalonSRX leftMaster;
  public VictorSPX rightSlave;
  public DigitalInput leftLimit, rightLimit;
  public DigitalInput topLimit, bottomLimit;

  public Elevator() {
    leftMaster = new TalonSRX(RobotMap.ElevatorMap.ID_LEFT_MASTER);
    rightSlave = new VictorSPX(RobotMap.ElevatorMap.ID_RIGHT_SLAVE);

    rightSlave.follow(leftMaster);

    leftMaster.setSensorPhase(false);

    topLimit = new DigitalInput(RobotMap.ElevatorMap.DIO_TOP_LIMIT);
    bottomLimit = new DigitalInput(RobotMap.ElevatorMap.DIO_BOTTOM_LIMIT);

    leftLimit = new DigitalInput(RobotMap.ElevatorMap.DIO_LEFT_LIMIT);
    rightLimit = new DigitalInput(RobotMap.ElevatorMap.DIO_RIGHT_LIMIT);

  }

  public boolean isLeftLimit() {
    return leftLimit.get();
  }

  public boolean isRightLimit() {
    return rightLimit.get();
  }

  public boolean atTop() {
    return !topLimit.get();
  }

  public boolean atBottom() {
    return !bottomLimit.get();
  }

  public void resetEncoder() {
    leftMaster.setSelectedSensorPosition(0);
  }

  public void stop() {
    leftMaster.set(ControlMode.PercentOutput, 0);
  }

  public void manualSetSpeed(double speed) {
    if (atTop() && speed > 0) {
      speed = 0;
    } else if (atBottom() && speed < 0) {
      speed = 0;
    } 
    leftMaster.set(ControlMode.PercentOutput, speed);
  }

  public void pidSet(double speed) {
    leftMaster.set(ControlMode.PercentOutput, speed);
  }

  public double pidGet() {
    return leftMaster.getSelectedSensorPosition();
  }

  @Override
  public void initDefaultCommand() {
  }
}
