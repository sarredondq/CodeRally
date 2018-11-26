import com.ibm.coderally.agent.DefaultCarAIAgent;
import com.ibm.coderally.api.agent.AIUtils;
import com.ibm.coderally.entity.cars.agent.Car;
import com.ibm.coderally.entity.obstacle.agent.Obstacle;
import com.ibm.coderally.geo.agent.CheckPoint;
import com.ibm.coderally.track.agent.Track;

public class Naruto2 extends DefaultCarAIAgent {

	@Override
	public void onCarCollision(Car other) {
		// Provide custom logic or remove method for default implementation.
		
		if (AIUtils.isCarAhead(getTrack(),getCar(),other)) {
			getCar().setTarget(AIUtils.getAlternativeLane(getCar().getCheckpoint(),getCar().getPosition()));
			getCar().setBrakePercent(0);
			getCar().setAccelerationPercent(100);
			
		}else {
			getCar().setTarget(getCar().getCheckpoint().getCenter());
			getCar().setBrakePercent(0);
			getCar().setAccelerationPercent(100);
		}
	}

	@Override
	public void onCheckpointUpdated(CheckPoint oldCheckpoint) {
		// Replace with custom logic or remove method for default implementation.
			getCar().setTarget(getCar().getCheckpoint().getCenter());
			getCar().setBrakePercent(0);
			getCar().setAccelerationPercent(100);
			
		
	}

	@Override
	public void onObstacleInProximity(Obstacle obstacle) {
		// Provide custom logic or remove method for default implementation.
		int actual = getCar().getAccelerationPercent();
		getCar().setAccelerationPercent(100);
		getCar().setTarget(AIUtils.getAlternativeLane(getCar().getCheckpoint(), getCar().getPosition()));
	
		if(getCar().getAcceleration().normalize()>50) {
			getCar().setAccelerationPercent(actual);
		}
	
		AIUtils.recalculateHeading(getCar());
	
	}

	@Override
	public void onOnTrack() {
		// Provide custom logic or remove method for default implementation.		
	}
	
	@Override
	public void onOffTrack() {
		// Provide custom logic or remove method for default implementation.
		getCar().setBrakePercent(5);
		//getCar().setAccelerationPercent(0);		
		getCar().setTarget(AIUtils.getClosestLane(getCar().getCheckpoint(), getCar().getPosition()));	
		
		
	}

	@Override
	public void onOpponentInProximity(Car car) {
		// Provide custom logic or remove method for default implementation.
		
		
		if (AIUtils.isCarAhead(getTrack(),getCar(),car)) {
			getCar().setTarget(AIUtils.getAlternativeLane(getCar().getCheckpoint(),getCar().getPosition()));
			getCar().setBrakePercent(0);
			getCar().setAccelerationPercent(100);
			
		}else {
			getCar().setTarget(getCar().getCheckpoint().getCenter());
			getCar().setBrakePercent(0);
			getCar().setAccelerationPercent(100);
		}
	
	}

	@Override
	public void onRaceStart() {

		// Replace with custom logic or remove method for default implementation.
		getCar().setTarget(getCar().getCheckpoint().getCenter());
		getCar().setBrakePercent(0);
		getCar().setAccelerationPercent(100);
		
		
	}

	@Override
	public void onTimeStep() {

		
		//  NOTE: Call getObstacles(...) or getCars(...) from any method for the latest position information
		
		// Replace with custom logic or remove method for default implementation.
		
			if(getCar().getAcceleration().normalize() > 110) {
				getCar().setBrakePercent(10);
				getCar().setAccelerationPercent(80);
			
				
			}else {
				getCar().setBrakePercent(0);
				getCar().setAccelerationPercent(100);
				
			}
		getCar().setTarget(getCar().getCheckpoint().getCenter());		
		AIUtils.recalculateHeading(getCar());
	}

	@Override
	public void init(Car car, Track track) {
		// Provide custom logic or remove method for default implementation.		
	}

	@Override
	public void onObstacleCollision(Obstacle obstacle) {
		// Provide custom logic or remove method for default implementation.		
	}

	@Override
	public void onStalled() {
		// Provide custom logic or remove method for default implementation.		
		if(getCar().getAcceleration().normalize() < 10) {
			getCar().setAccelerationPercent(100);
			getCar().setBrakePercent(0);
			getCar().setTarget(getCar().getCheckpoint().getCenter());
			AIUtils.recalculateHeading(getCar());
		}
	}

}
