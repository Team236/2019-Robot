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
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
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

  public TalonSRX masterElevator;
  public VictorSPX slaveElevator1, slaveElevator2;
  public DoubleSolenoid pogoClutch;

  private DigitalInput topLimit, bottomLimit;

  public Elevator() {
    masterElevator = new TalonSRX(RobotMap.ElevatorMap.ID_T_MASTER);
    slaveElevator1 = new VictorSPX(RobotMap.ElevatorMap.ID_V_SLAVE_1);
    slaveElevator2 = new VictorSPX(RobotMap.ElevatorMap.ID_V_SLAVE_2);

    slaveElevator1.follow(masterElevator);
    slaveElevator2.follow(masterElevator);

    masterElevator.setSensorPhase(false);

    pogoClutch = new DoubleSolenoid(RobotMap.ElevatorMap.SOL_FWD, RobotMap.ElevatorMap.SOL_REV);

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
    return masterElevator.getSelectedSensorPosition();
  }

  public double getHeight() {
    return getEncoder() * RobotMap.ElevatorMap.DISTANCE_PER_PULSE;
  }

  public void resetEncoder() {
    masterElevator.setSelectedSensorPosition(0);
  }

  public void resetAtBottom() {
    if (atBottom()) {
      resetEncoder();
    }
  }

  public void stop() {
    masterElevator.set(ControlMode.PercentOutput, 0);
  }

  public void setSpeed(double speed) {
    if ((atTop() || getHeight() >= RobotMap.ElevatorMap.TOP_HEIGHT || Robot.cargo.isExtended()) && speed > 0) {
      speed = 0;
    } else if (atBottom() && speed < 0) {
      speed = 0;
    }
    masterElevator.set(ControlMode.PercentOutput, speed);
  }

  public void pidSet(double speed) {
    masterElevator.set(ControlMode.PercentOutput, speed);
  }

  public double pidGet() {
    return getHeight();
  }

  public void setkP(double _kP) {
    masterElevator.config_kP(0, _kP, 0);
  }

  public void setkI(double _kI) {
    masterElevator.config_kI(0, _kI, 0);
  }

  public void setkD(double _kD) {
    masterElevator.config_kD(0, _kD, 0);
  }

  public void setkF(double _kF) {
    masterElevator.config_kF(0, _kF, 0);
  }

  public void setMotnParams(double _kP, double _kI, double _kD, double _kF) {
    setkP(_kP);
    setkI(_kI);
    setkD(_kD);
    setkF(_kF);
  }

  public void setCV(int cruiseVel) {
    masterElevator.configMotionCruiseVelocity(cruiseVel, 0);
  }

  public void setAccel(int accel) {
    masterElevator.configMotionAcceleration(accel, 0);
  }

  public void setDistMotnMagic(double dist) {
    masterElevator.set(ControlMode.MotionMagic, dist);
  }

  public void engageClutch() {
    pogoClutch.set(Value.kForward);
  }

  public void disengageClutch() {
    pogoClutch.set(Value.kReverse);
  }

  public boolean isClutch() {
    return pogoClutch.get() == Value.kForward;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new StopElevator());
  }
}
