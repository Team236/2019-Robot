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
public class Pogo extends Subsystem {
  public TalonSRX leftExtendMotor, rightExtendMotor;
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
    if ((leftAtTop() || getLeftPogoEncoder() > 0) && leftSpeed < 0) {
      leftSpeed = 0;
      resetLeftEncoder();
    } else if ((leftAtBottom() || getLeftPogoEncoder() >= RobotMap.PogoMap.MAX_COUNT) && leftSpeed > 0) {
      leftSpeed = 0;
    }
    leftExtendMotor.set(ControlMode.PercentOutput, leftSpeed);
  }

  public void setRightSpeed(double rightSpeed) {
    if ((rightAtTop() || getRightPogoEncoder() > 0) && rightSpeed < 0) {
      rightSpeed = 0;
      resetRightEncoder();
    } else if (rightAtBottom() && rightSpeed > 0) {
      rightSpeed = 0;
    }
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

  public void resetLeftEncoder() {
    leftExtendMotor.setSelectedSensorPosition(0);
  }

  public void resetRightEncoder() {
    rightExtendMotor.setSelectedSensorPosition(0);
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

  @Override
  public void initDefaultCommand() {

  }
}
