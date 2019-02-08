/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Cargo extends Subsystem {
  // TODO 1 button to extend and retract
  public TalonSRX cargoMotor;
  public DoubleSolenoid cargoSolenoid;
  public DigitalInput cargoLimit;

  public Cargo() {
    cargoMotor = new TalonSRX(RobotMap.CargoMap.ID_CARGO_MOTOR);
    cargoSolenoid = new DoubleSolenoid(RobotMap.CargoMap.SOL_FWD, RobotMap.CargoMap.SOL_REV);
    cargoLimit = new DigitalInput(RobotMap.CargoMap.DIO_CARGO_LIMIT);
  }

  public void setSpeed(double speed) {
    cargoMotor.set(ControlMode.PercentOutput, speed);
    ;
  }

  public void stop() {
    setSpeed(0);
  }

  public void extend() {
    cargoSolenoid.set(Value.kForward);
  }

  public void retract() {
    cargoSolenoid.set(Value.kReverse);
  }

  public boolean isLimit() {
    return !cargoLimit.get();
  }

  @Override
  public void initDefaultCommand() {
  }
}
