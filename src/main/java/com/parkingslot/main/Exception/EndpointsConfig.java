package com.parkingslot.main.Exception;

public class EndpointsConfig {
	
	public static final String SET_AVAILABILITY="/setAvailability/{id}";
	public static final String UPDATE_AVAILABILITY="/updateAvailability/{sid}/availid/{aid}";
	public static final String BOOK_SLOTS="/buildings/{bid}/floor/{fid}/slots/{sid}";
	public static final String CANCEL_BOOKING="/bookingDelete/{id}";
	public static final String ADD_BUILDING="/addBuilding";
	public static final String FETCH_BOOKING="/getBuilding";
	public static final String DELETE_BUILDING="/deleteBuilding/{id}";
	public static final String ADD_FLOORS="/addfloors/{id}";
	public static final String DELETE_FLOOR="/building/{bid}/deleteFloor/{fid}";
	public static final String ADD_SLOTS="/addSlot/{name}/floor/{id}";
	public static final String DELETE_SLOT="/slot/{sid}";
	

}
