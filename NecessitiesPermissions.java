package kdx7214.necessities;

public class NecessitiesPermissions implements NecessitiesPermissionsInterface {

	public static NecessitiesPermissionsInterface Instance = new NecessitiesPermissions() ;
	private final static String[] permissions = {"necessities.positive","-necessities.negative"} ;

	@Override
	public boolean hasPermission(String player, String permission) {
		return true;
	} // public boolean hasPermission(...)

} // public class NecessitiesPermissions(...)
