package Events;

import java.util.Random;

import BeNote.Aeroport;
import BeNote.Entities;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.simulation.core.SortedList;

public class EvtApproach extends Events {

	public EvtApproach() {
		this.name =  "Plane approaching phase";
		this.start =  new LogicalDateTime("01/01/2015 00:00:00.0000");
		
	}
	
	public EvtApproach(LogicalDateTime startDate, Entities plane) {
		this.name = "Plane approaching phase";
		this.start = startDate;
		this.plane = plane;
	}

	@Override
	public String doSomething(SortedList<Events> agenda,Aeroport aero) {
		String log = "approche de l'avion "+plane.ID;
		int tmpAppr=approachDuration();
		if (aero.meteo == "mauvaise")
		{
			tmpAppr=2*tmpAppr;
		}
		agenda.add(new EvtLanding(start.add(LogicalDuration.ofMinutes(tmpAppr)),plane));
		return log;
	}

	private int approachDuration() {
		Random alea =new Random();
		int dur = 2+alea.nextInt(4);
		
		return dur;
	}


}