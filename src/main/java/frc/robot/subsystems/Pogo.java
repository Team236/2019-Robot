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
  // TODO: logic for BOTH sides

  public TalonSRX leftExtendMotor, rightExtendMotor;
  public VictorSPX leftRollMotor, rightRollMotor;
  private DigitalInput topLimit, bottomLimit;
  public DigitalInput rollSensor;

  public Pogo() {
    leftExtendMotor = new TalonSRX(RobotMap.PogoMap.ID_LEFT_EXTEND_MOTOR);
    rightExtendMotor = new TalonSRX(RobotMap.PogoMap.ID_RIGHT_EXTEND_MOTOR);
    leftRollMotor = new VictorSPX(RobotMap.PogoMap.ID_LEFT_ROLL_MOTOR);
    rightRollMotor = new VictorSPX(RobotMap.PogoMap.ID_RIGHT_ROLL_MOTOR);

    topLimit = new DigitalInput(RobotMap.PogoMap.DIO_TOP_LIMIT);
    bottomLimit = new DigitalInput(RobotMap.PogoMap.DIO_BOTTOM_LIMIT);

    rollSensor = new DigitalInput(RobotMap.PogoMap.DIO_SENSOR);

    leftExtendMotor.setSensorPhase(false);
    rightExtendMotor.setSensorPhase(false);
  }

  public boolean atTop() {
    return !topLimit.get();
  }

  public boolean atBottom() {
    return !bottomLimit.get();
  }

  public void setPogoSpeed(double pogoSpeed) {
    if (atTop() && pogoSpeed > 0) {
      pogoSpeed = 0;
      resetEncoders();
    } else if (atBottom() && pogoSpeed < 0) {
      pogoSpeed = 0;
    }
    leftExtendMotor.set(ControlMode.PercentOutput, pogoSpeed);
    rightExtendMotor.set(ControlMode.PercentOutput, pogoSpeed);
  }

  public void stopPogo() {
    setPogoSpeed(0);
  }

  public void setRollSpeed(double rollSpeed) {
    leftRollMotor.set(ControlMode.PercentOutput, rollSpeed);
    rightRollMotor.set(ControlMode.PercentOutput, rollSpeed);
  }

  public void stopRoll() {
    setRollSpeed(0);
  }

  public double getLeftPogoDistance() {
    return leftExtendMotor.getSelectedSensorPosition() * RobotMap.PogoMap.DISTANCE_PER_PULSE;
  }

  public double getRightPogoDistance() {
    return rightExtendMotor.getSelectedSensorPosition() * RobotMap.PogoMap.DISTANCE_PER_PULSE;
  }

  public void resetEncoders() {
    leftExtendMotor.setSelectedSensorPosition(0);
    rightExtendMotor.setSelectedSensorPosition(0);
  }

  public void resetEncodersAtTop() {
    if (atTop()) {
      leftExtendMotor.setSelectedSensorPosition(0);
      rightExtendMotor.setSelectedSensorPosition(0);
    }
  }

  @Override
  public void initDefaultCommand() {

  }
}
