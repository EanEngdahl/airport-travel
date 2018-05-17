package airportsysmodel;

public class Aircraft {

		/* will be on of s, m, l */
		private char aircraftSize;
		private AircraftSectionList sectionList;
		
		public Aircraft(char aircraftSize_, int seatsFilledPerSection_[]) {
			aircraftSize = aircraftSize_;
			sectionList = new AircraftSectionList(aircraftSize_,
					seatsFilledPerSection_);
		}
		
}
