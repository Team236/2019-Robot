/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.cargo.CargoEject;
import frc.robot.commands.cargo.CargoExtend;
import frc.robot.commands.cargo.CargoIntake;
import frc.robot.commands.cargo.CargoRetract;
import frc.robot.commands.elevator.ElevatorWithThumbstick;
import frc.robot.commands.hatch.HatchExtend;
import frc.robot.commands.hatch.HatchExtendAndRetract;
import frc.robot.commands.hatch.HatchRetract;
import frc.robot.commands.hatch.HatchScore;
import frc.robot.commands.pogo.PogoWithThumbstick;
import frc.robot.commands.pogo.Roll;
import lib.oi.LogitechF310;
import lib.oi.Thrustmaster;

public class OI {
  public Thrustmaster leftStick, rightStick;
  public LogitechF310 controller;

  public OI() {
    leftStick = new Thrustmaster(RobotMap.JoystickMap.USB_LEFT);
    rightStick = new Thrustmaster(RobotMap.JoystickMap.USB_RIGHT);
    controller = new LogitechF310(RobotMap.JoystickMap.USB_CONTROLLER);

    controller.back.whileHeld(new PogoWithThumbstick());
    controller.rb.whileHeld(new Roll());

    controller.a.whileHeld(new CargoIntake());
    controller.b.whileHeld(new CargoEject());

    // controller.x.whenPressed(new CargoExtend());
    // controller.y.whenPressed(new CargoRetract());
    controller.x.whileHeld(new HatchScore());

    controller.start.whileHeld(new ElevatorWithThumbstick());

    controller.lb.whenPressed(new HatchExtendAndRetract());

  }

}
