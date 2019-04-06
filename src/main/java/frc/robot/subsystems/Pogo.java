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
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
/*public class Pogo extends Subsystem {
  /*public TalonSRX leftExtendMotor, rightExtendMotor;
  public VictorSPX leftRollMotor, rightRollMotor;
  private DigitalInput leftTopLimit, rightToplimit, leftBottomLimit, rightBottomLimit;
  public DigitalInput rollSensor;

  public Pogo() {
    leftExtendMotor = new TalonSRX(RobotMap.PogoMap.ID_T_LEFT_EXTEND_MOTOR);
    rightExtendMotor = new TalonSRX(RobotMap.PogoMap.ID_T_RIGHT_EXTEND_MOTOR);
    leftRollMotor = new VictorSPX(RobotMap.PogoMap.ID_V_LEFT_ROLL_MOTOR);
    rightRollMotor = new VictorSPX(RobotMap.PogoMap.ID_V_RIGHT_ROLL_MOTOR);

    leftTopLimit = new DigitalInput(RobotMap.PogoMap.DIO_LEFT_TOP_LIMIT);
    rightToplimit = new DigitalInput(RobotMap.PogoMap.DIO_RIGHT_TOP_LIMIT);
    leftBottomLimit = new DigitalInput(RobotMap.PogoMap.DIO_LEFT_BOTTOM_LIMIT);
    rightBottomLimit = new DigitalInput(RobotMap.PogoMap.DIO_RIGHT_BOTTOM_LIMIT);

    rollSensor = new DigitalInput(RobotMap.PogoMap.DIO_SENSOR);

    leftExtendMotor.setSensorPhase(false);
    rightExtendMotor.setSensorPhase(true);
  }

  public boolean leftAtTop() {
    return leftTopLimit.get();
  }

  public boolean rightAtTop() {
    return rightToplimit.get();
  }

  public boolean atTop() {
    return leftAtTop() && rightAtTop();
  }

  public boolean leftAtBottom() {
    return leftBottomLimit.get();
  }

  public boolean rightAtBottom() {
    return rightBottomLimit.get();
  }

  public boolean atBottom() {
    return leftAtBottom() && rightAtBottom();
  } 

  public boolean atPlatform() {
    return rollSensor.get();
  }

  public void setLeftPogoSpeed(double leftSpeed) {
    // || getLeftPogoDistance() < 0
    //  || getLeftPogoEncoder() > 0
    if ((leftAtTop()) && leftSpeed < 0) {
      leftSpeed = 0;
      resetLeftEncoder();
    } else if (leftAtBottom() && leftSpeed > 0) {
      leftSpeed = 0;
    }
    leftExtendMotor.set(ControlMode.PercentOutput, leftSpeed); 
  }

  public void setRightSpeed(double rightSpeed) {
    //  || getRightPogoEncoder() > 0
     if ((rightAtTop()) && rightSpeed < 0) {
      rightSpeed = 0;
      resetRightEncoder();
    } else if (rightAtBottom() && rightSpeed > 0) {
      rightSpeed = 0;
    }
    System.out.println("right pogo extend: " + rightSpeed);
    rightExtendMotor.set(ControlMode.PercentOutput, rightSpeed); 
  }

  public void setPogoSpeed(double leftSpeed, double rightSpeed) {
    setLeftPogoSpeed(leftSpeed);
    setRightSpeed(rightSpeed);

  }

  public void stopPogo() {
    setPogoSpeed(0, 0);
  }

  public void setRollSpeed(double rollSpeed) {
    leftRollMotor.set(ControlMode.PercentOutput, rollSpeed);
    rightRollMotor.set(ControlMode.PercentOutput, rollSpeed);
  }

  public void stopRoll() {
    setRollSpeed(0);
  }

  public int getLeftPogoEncoder() {
    return leftExtendMotor.getSelectedSensorPosition();
  }

  public int getRightPogoEncoder() {
    return rightExtendMotor.getSelectedSensorPosition();
  }

  public double getLeftPogoDistance() {
    return getLeftPogoEncoder() * RobotMap.PogoMap.DISTANCE_PER_PULSE;
  }

  public double getRightPogoDistance() {
    return getRightPogoEncoder() * RobotMap.PogoMap.DISTANCE_PER_PULSE;
  }

  public double getAvgDist() {
    return getLeftPogoDistance() + getRightPogoDistance() / 2;
  }

  public void resetLeftEncoder() {
    if (leftAtTop()) {
      leftExtendMotor.setSelectedSensorPosition(0);
    }   
  }

  public void resetRightEncoder() {
    if (rightAtTop()) {
      rightExtendMotor.setSelectedSensorPosition(0);
    } 
  }

  public void resetEncoders() {
    resetLeftEncoder();
    resetRightEncoder();
  }

  public void resetEncodersAtTop() {
    if (atTop()) {
      resetEncoders();
    }
  }

  public void setkP(double _kP) {
    leftExtendMotor.config_kP(0, _kP, 0);
    rightExtendMotor.config_kP(0, _kP, 0);
  }

  public void setkI(double _kI) {
    leftExtendMotor.config_kI(0, _kI, 0);
    rightExtendMotor.config_kI(0, _kI, 0);
  }

  public void setkD(double _kD) {
    leftExtendMotor.config_kD(0, _kD, 0);
    rightExtendMotor.config_kD(0, _kD, 0);
  }

  public void setkF(double _kF_L, double _kF_R) {
    leftExtendMotor.config_kF(0, _kF_L, 0);
    rightExtendMotor.config_kF(0, _kF_R, 0);
  }

  public void setMotnParams(double _kP, double _kI, double _kD, double _kF_L, double _kF_R) {
    setkP(_kP);
    setkI(_kI);
    setkD(_kD);
    setkF(_kF_L, _kF_R);
  }

  public void setCV(int cruiseVelocity) {
    leftExtendMotor.configMotionCruiseVelocity(cruiseVelocity, 0);
    rightExtendMotor.configMotionCruiseVelocity(cruiseVelocity, 0);
  }

  public void setAccel(int accel) {
    leftExtendMotor.configMotionAcceleration(accel, 0);
    rightExtendMotor.configMotionAcceleration(accel, 0);
  }

  public void setDistMotnMagic(double dist) {
    leftExtendMotor.set(ControlMode.MotionMagic, dist);
    rightExtendMotor.set(ControlMode.MotionMagic, dist);
  }

  @Override
  public void initDefaultCommand() {

  }*/
// }*/
