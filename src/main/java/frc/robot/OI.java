/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.auto.GyroDrive;
import frc.robot.commands.camera.TiltFrontCamera;
import frc.robot.commands.camera.TiltRearCamera;
import frc.robot.commands.cargo.CargoEject;
import frc.robot.commands.cargo.CargoExtend;
import frc.robot.commands.cargo.CargoExtendAndRetract;
import frc.robot.commands.cargo.CargoIntake;
import frc.robot.commands.cargo.CargoRetract;
import frc.robot.commands.drive.DriveWithThumbsticks;
import frc.robot.commands.elevator.ElevatorToHeight;
import frc.robot.commands.elevator.ElevatorWithThumbstick;
import frc.robot.commands.elevator.IncrementUp;
import frc.robot.commands.hatch.HatchExtend;
import frc.robot.commands.hatch.HatchExtendAndRetract;
import frc.robot.commands.hatch.HatchRetract;
import frc.robot.commands.hatch.HatchScore;
import frc.robot.commands.pogo.PogoExtend;
import frc.robot.commands.pogo.PogoRetract;
import frc.robot.commands.pogo.PogoWithThumbstick;
import frc.robot.commands.pogo.Roll;
import lib.oi.LogitechF310;
import lib.oi.Thrustmaster;
import lib.oi.triggers.JoystickPOV;
import lib.oi.triggers.TwoButton;
import lib.oi.triggers.JoystickPOV.Direction;
import lib.pid.PIDParameters;

public class OI {
  public Thrustmaster leftStick, rightStick;
  public LogitechF310 controller;

  public OI() {   
    leftStick = new Thrustmaster(RobotMap.JoystickMap.USB_LEFT);
    rightStick = new Thrustmaster(RobotMap.JoystickMap.USB_RIGHT);
    controller = new LogitechF310(RobotMap.JoystickMap.USB_CONTROLLER);

    // DRIVE
    TwoButton thumbDrive = new TwoButton(controller.lb, controller.rb);
    thumbDrive.whileHeld(new DriveWithThumbsticks());
    rightStick.right.whileHeld(new GyroDrive(RobotMap.AutoMap.GYRO_DRIVE_KP, 240, .25));

    // POGO
    // extends when go up on controller
    // TODO: button for auto endgame
    controller.back.whileHeld(new PogoWithThumbstick()); // right is forward
    // controller.y.whileHeld(new PogoExtend());
    // controller.b.whileHeld(new PogoRetract());
    // controller.a.whileHeld(new Roll());

    // CARGO
    leftStick.middle.whileHeld(new CargoIntake());
    leftStick.trigger.whileHeld(new CargoEject());
    controller.lb.toggleWhenPressed(new CargoExtendAndRetract());

    // HATCH
    rightStick.trigger.whileHeld(new HatchScore());
    controller.rb.toggleWhenPressed(new HatchExtendAndRetract());

    // ELEVATOR
    controller.start.whileHeld(new ElevatorWithThumbstick());
    JoystickPOV cargo1 = new JoystickPOV(controller, Direction.DOWN);
    JoystickPOV cargoShip = new JoystickPOV(controller, Direction.LEFT);
    JoystickPOV cargo2 = new JoystickPOV(controller, Direction.RIGHT);
    JoystickPOV cargo3 = new JoystickPOV(controller, Direction.UP);

    // C1
    cargo1.whileHeld(new ElevatorToHeight(6 + RobotMap.ElevatorMap.CARGO_OFFSET, RobotMap.ElevatorMap.HEIGHT_MARGIN, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
    // H1, cargo floor intake (bottom)
    controller.a.whileHeld(new ElevatorToHeight(0, RobotMap.ElevatorMap.HEIGHT_MARGIN, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
    // CARGO SHIP
    cargoShip.whileHeld(new ElevatorToHeight(19, RobotMap.ElevatorMap.HEIGHT_MARGIN, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
    // C2
    cargo2.whileHeld(new ElevatorToHeight(33 + RobotMap.ElevatorMap.CARGO_OFFSET, RobotMap.ElevatorMap.HEIGHT_MARGIN, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
    // H2
    controller.x.whileHeld(new ElevatorToHeight(33, RobotMap.ElevatorMap.HEIGHT_MARGIN, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
    // C3
    cargo3.whileHeld(new ElevatorToHeight(62 + RobotMap.ElevatorMap.CARGO_OFFSET, RobotMap.ElevatorMap.HEIGHT_MARGIN, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
    // H3
    controller.y.whileHeld(new ElevatorToHeight(62, RobotMap.ElevatorMap.HEIGHT_MARGIN, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
    // Increment up by cargo offset
    controller.b.whileHeld(new IncrementUp(RobotMap.ElevatorMap.CARGO_OFFSET));
    // controller.b.whileHeld(new ElevatorToHeight(6, RobotMap.ElevatorMap.HEIGHT_MARGIN, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.CLIMB_PARAMS));

    // CAMERA - rotating on servo
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
        