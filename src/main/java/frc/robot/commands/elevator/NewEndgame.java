/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.cargo.CargoExtend;
// import frc.robot.commands.pogo.Roll;
import frc.robot.commands.cargo.CargoIntake;
import frc.robot.commands.cargo.CargoRetract;
import frc.robot.commands.drive.SetDriveSpeed;

public class NewEndgame extends CommandGroup {
  /**
   * Add your docs here.
   */
  public NewEndgame(int level) {
    double startHeight = 0;
    double endHeight = 0;

    // sets start/end height based on level
    if (level == 3) {
      // startHeight = RobotMap.PogoMap.LV3_START;
      startHeight = RobotMap.PogoMap.LV3_START;
      endHeight = RobotMap.PogoMap.LV3_END;
    } else if (level == 2) {
      startHeight = RobotMap.PogoMap.LV2_START;
      endHeight = RobotMap.PogoMap.LV2_END;
    }

    // brings elevator down to start height
    addSequential(new ElevatorToHeight(startHeight, 3, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));

    addSequential(new CargoExtend());

    if (level == 3) {
      addSequential(new EngageClutch(24.25));
    } else if (level == 2) {
      addSequential(new EngageClutch(11.2));
    }

    // brings elevator and pogos down to lift robots
    addParallel(new CargoIntake(), 5);
    addSequential(new ElevatorToHeight(endHeight, 1, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.CLIMB_PARAMS), 5);

    // addSequential(new ElevatorToHeight(endHeight + 5, 3, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));
    // addSequential(new DisengageClutch());

  }
}
