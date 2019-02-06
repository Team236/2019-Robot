/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.camera.TiltFrontCamera;
import frc.robot.commands.camera.TiltRearCamera;
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
import lib.oi.triggers.JoystickPOV;
import lib.oi.triggers.JoystickPOV.Direction;

public class OI {
  public Thrustmaster leftStick, rightStick;
  public LogitechF310 controller;

  public OI() {
    leftStick = new Thrustmaster(RobotMap.JoystickMap.USB_LEFT);
    rightStick = new Thrustmaster(RobotMap.JoystickMap.USB_RIGHT);
    controller = new LogitechF310(RobotMap.JoystickMap.USB_CONTROLLER);

    // POGO
    controller.back.whileHeld(new PogoWithThumbstick());
    controller.rb.whileHeld(new Roll());

    // CARGO
    controller.a.whileHeld(new CargoIntake());
    controller.b.whileHeld(new CargoEject());

    controller.x.whenPressed(new CargoExtend());
    controller.y.whenPressed(new CargoRetract());

    // HATCH
    controller.x.whileHeld(new HatchScore());
    controller.lb.whenPressed(new HatchExtendAndRetract());

    // ELEVATOR
    controller.start.whileHeld(new ElevatorWithThumbstick());

    // CAMERA
    JoystickPOV raiseFrontCam = new JoystickPOV(rightStick, Direction.UP);
    JoystickPOV lowerFrontCam = new JoystickPOV(rightStick, Direction.DOWN);
    JoystickPOV raiseRearCam = new JoystickPOV(leftStick, Direction.UP);
    JoystickPOV lowerRearCam = new JoystickPOV(leftStick, Direction.DOWN);
    raiseFrontCam.whenPressed(new TiltFrontCamera(0.0));
    lowerFrontCam.whenPressed(new TiltFrontCamera(1.0));
    raiseRearCam.whenPressed(new TiltRearCamera(0.0));
    lowerRearCam.whenPressed(new TiltRearCamera(1.0));

  }

}
