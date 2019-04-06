/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.auto.GyroDrive;
import frc.robot.commands.auto.LimelightDrive;
import frc.robot.commands.camera.TiltFrontCamera;
import frc.robot.commands.camera.TiltRearCamera;
import frc.robot.commands.cargo.CargoEject;
import frc.robot.commands.cargo.CargoExtend;
import frc.robot.commands.cargo.CargoExtendAndRetract;
import frc.robot.commands.cargo.CargoIntake;
import frc.robot.commands.cargo.CargoRetract;
import frc.robot.commands.drive.DriveWithThumbsticks;
import frc.robot.commands.drive.DriveWithVision;
import frc.robot.commands.elevator.ClutchToggle;
import frc.robot.commands.elevator.ElevatorToHeight;
import frc.robot.commands.elevator.ElevatorWithThumbstick;
import frc.robot.commands.elevator.EngageClutch;
import frc.robot.commands.elevator.IncrementUp;
import frc.robot.commands.elevator.NewEndgame;
import frc.robot.commands.hatch.HatchExtend;
import frc.robot.commands.hatch.HatchExtendAndRetract;
import frc.robot.commands.hatch.HatchRetract;
import frc.robot.commands.hatch.HatchScore;
import frc.robot.commands.pogo.AutoEndGame;
// import frc.robot.commands.pogo.GyroPogo;
// import frc.robot.commands.pogo.PogoExtend;
// import frc.robot.commands.pogo.PogoRetract;
// import frc.robot.commands.pogo.PogoWithThumbstick;
// import frc.robot.commands.pogo.Roll;
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
    // controller.b.whileHeld(new LimelightDrive(RobotMap.AutoMap.LIME_DRIVE_KP, RobotMap.AutoMap.LIME_DRIVE_SPEED));
    rightStick.right.whileHeld(new DriveWithVision());
    // TODO: tune GyroDrive (last yr kP was .04)
    // leftStick.left.whileHeld(new GyroDrive(RobotMap.AutoMap.GYRO_DRIVE_KP, 240, .5));

    // POGO
    // extends when go up on controller
    // TODO: button for auto endgame
    // controller.back.whileHeld(new PogoWithThumbstick()); // right is forward
    // rightStick.left.whileHeld(new GyroPogo(RobotMap.PogoMap.KP, 1000, .3)); // 1000 native units should be about 10 in
    // controller.rightPress.whileHeld(new AutoEndGame());

    // CARGO
    leftStick.middle.whileHeld(new CargoIntake());
    leftStick.trigger.whileHeld(new CargoEject());
    controller.lb.toggleWhenPressed(new CargoExtendAndRetract());
    // controller.lb.whenPressed(new CargoExtendAndRetract());

    // HATCH
    rightStick.trigger.whileHeld(new HatchScore());
    controller.rb.toggleWhenPressed(new HatchExtendAndRetract());
    // rightStick.middle.toggleWhenPressed(new HatchExtendAndRetract());
    rightStick.middle.toggleWhenPressed(new HatchExtendAndRetract());
    // controller.start.toggleWhenPressed(new HatchScore());

    // ELEVATOR
    controller.start.whileHeld(new ElevatorWithThumbstick());
    JoystickPOV cargo1 = new JoystickPOV(controller, Direction.DOWN);
    JoystickPOV cargoShip = new JoystickPOV(controller, Direction.LEFT);
    JoystickPOV cargo2 = new JoystickPOV(controller, Direction.RIGHT);
    JoystickPOV cargo3 = new JoystickPOV(controller, Direction.UP);
    controller.rightPress.whenPressed(new NewEndgame(3));
    controller.leftPress.whenPressed(new NewEndgame(2));
    controller.b.toggleWhenPressed(new ClutchToggle());

    // C1
    cargo1.whileHeld(new ElevatorToHeight(6 + RobotMap.ElevatorMap.CARGO_OFFSET + RobotMap.ElevatorMap.OFFSET, RobotMap.ElevatorMap.HEIGHT_MARGIN, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
    // H1, cargo floor intake (bottom)
    controller.a.whileHeld(new ElevatorToHeight(0 + RobotMap.ElevatorMap.OFFSET, RobotMap.ElevatorMap.HEIGHT_MARGIN, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
    // CARGO SHIP
    cargoShip.whileHeld(new ElevatorToHeight(25 + RobotMap.ElevatorMap.OFFSET, RobotMap.ElevatorMap.HEIGHT_MARGIN, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
    // C2
    cargo2.whileHeld(new ElevatorToHeight(33 + RobotMap.ElevatorMap.CARGO_OFFSET + RobotMap.ElevatorMap.OFFSET, RobotMap.ElevatorMap.HEIGHT_MARGIN, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
    // H2
    controller.x.whileHeld(new ElevatorToHeight(33 + RobotMap.ElevatorMap.OFFSET, RobotMap.ElevatorMap.HEIGHT_MARGIN, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
    // C3
    cargo3.whileHeld(new ElevatorToHeight(62 + RobotMap.ElevatorMap.CARGO_OFFSET + RobotMap.ElevatorMap.OFFSET, RobotMap.ElevatorMap.HEIGHT_MARGIN, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
    // H3
    controller.y.whileHeld(new ElevatorToHeight(62 + RobotMap.ElevatorMap.OFFSET, RobotMap.ElevatorMap.HEIGHT_MARGIN, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
    // Increment up by cargo offset
    // ENDGAME ELEVATOR POSITION
    // controller.b.whileHeld(new ElevatorToHeight(6 + RobotMap.ElevatorMap.OFFSET, RobotMap.ElevatorMap.HEIGHT_MARGIN, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.CLIMB_PARAMS));

    // CAMERA - rotating on servo
    // JoystickPOV raiseFrontCam = new JoystickPOV(rightStick, Direction.UP);
    // JoystickPOV lowerFrontCam = new JoystickPOV(rightStick, Direction.DOWN);
    // JoystickPOV raiseRearCam = new JoystickPOV(leftStick, Direction.UP);
    // JoystickPOV lowerRearCam = new JoystickPOV(leftStick, Direction.DOWN);
    // raiseFrontCam.whenPressed(new TiltFrontCamera(0.0));
    // lowerFrontCam.whenPressed(new TiltFrontCamera(1.0));
    // raiseRearCam.whenPressed(new TiltRearCamera(0.0));
    // lowerRearCam.whenPressed(new TiltRearCamera(1.0));
  }

}
        