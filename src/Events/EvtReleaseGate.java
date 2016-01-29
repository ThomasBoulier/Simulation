package Events;

import BeNote.Aeroport;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.simulation.core.SortedList;

public class EvtReleaseGate extends Events{
	
	public int gateID;

	public EvtReleaseGate() {
		this.name =  "Gate released";
		this.start =  new LogicalDateTime("01/01/2015 00:00:00.0000");
	
	}

	public EvtReleaseGate(LogicalDateTime startDate, int gateID) {
		this.name = "Gate released";
		this.start = startDate;
		this.gateID = gateID;
	}

	@Override
	public String doSomething(SortedList<Events> agenda, Aeroport aero) {
		String log = "liberation de la porte "+(gateID +1);
		if(aero.waitingListGate.size()==0)
		{
			aero.facilities.gates[gateID].status = "libre";
		}else
		{
			aero.facilities.gates[gateID].status = "occupe";
			
			this.plane = aero.waitingListGate.first();
			aero.waitingListGate.remove(aero.waitingListGate.first());

			agenda.add(new EvtRelease_P_TW1(start.add(LogicalDuration.ofMinutes(5))));
			agenda.add(new EvtUnload(start.add(LogicalDuration.ofMinutes(5)),plane));

		}
		return log;
	}
}