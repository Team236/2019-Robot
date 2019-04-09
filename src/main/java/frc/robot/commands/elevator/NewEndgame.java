/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
// import frc.robot.commands.pogo.Roll;
import frc.robot.commands.cargo.CargoIntake;

public class NewEndgame extends CommandGroup {
  /**
   * Add your docs here.
   */
  public NewEndgame(int level) {
    double startHeight = 0;
    double endHeight = 0;

    // sets start/end height based on level
    if (level == 3) {
      startHeight = RobotMap.PogoMap.LV3_START;
      endHeight = RobotMap.PogoMap.LV3_END;
    } else if (level == 2) {
      startHeight = RobotMap.PogoMap.LV2_START;
      endHeight = RobotMap.PogoMap.LV2_END;
    }

    // brings elevator down to start height
    // addSequential(new ElevatorToHeight(startHeight, 1, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.DOWN_PARAMS));

    // addSequential(new EngageClutch());

    // brings elevator and pogos down to lift robots
    addSequential(new ElevatorToHeight(endHeight, 1, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.CLIMB_PARAMS));

    addParallel(new CargoIntake(), 1);
    // addParallel(new NewPogoRoll(1));

    // continues holding robot up while it rolls forward
    addSequential(new ElevatorToHeight(endHeight, 1, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.CLIMB_PARAMS));

    addSequential(new DisengageClutch());

    addSequential(new ElevatorToHeight(endHeight + 3, 1, RobotMap.ElevatorMap.UP_PARAMS, RobotMap.ElevatorMap.CLIMB_PARAMS));
  }
}
