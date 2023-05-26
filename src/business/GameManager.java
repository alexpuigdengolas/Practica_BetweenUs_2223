package business;


import business.entities.Game;
import business.entities.character.Character;
import business.entities.character.Impostor;
import business.entities.character.Npc;
import business.entities.map.Cell;
import persistance.Conn.GameDAO;
import persistance.Conn.GameSQLDAO;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Esta clase representara el gestor de las partidas
 */
public class GameManager {
    private final GameDAO gameDAO;
    private PlayerManager playerManager;
    private MapManager mapManager;

    private NpcManager npcManager;

    private final UserManager userManager;

    private String gameName;
    private final StatisticsManager statisticsManager;

    /**
     * Este es el constructor de la clase de gestor de partidas
     * @param userManager el gestor de los ususarios
     * @param statisticsManager el gestor de estadisticas
     */
    public GameManager(UserManager userManager,StatisticsManager statisticsManager) {
        this.gameDAO = new GameSQLDAO();
        this.userManager = userManager;
        this.statisticsManager = statisticsManager;
    }

    /**
     * Getter del gestor de usuario
     * @return el gestor de usuario
     */
    public UserManager getUserManager(){
        return userManager;
    }

    /**
     * Setter del gestor de jugadores
     * @param playerManager el gestor del usuario
     */
    public void setPlayerManager(PlayerManager playerManager){
        this.playerManager = playerManager;
    }

    /**
     * Getter del gestor de jugadores
     * @return el gestor de jugadores
     */
    public PlayerManager getPlayerManager(){
        return playerManager;
    }

    /**
     * Setter del gestor de mapas
     * @param mapManager el gestor de mapas
     */
    public void setMapManager(MapManager mapManager){
        this.mapManager = mapManager;
    }

    /**
     * Getter del gestor de mapas
     * @return el gestor de mapas
     */
    public MapManager getmapManager(){
        return mapManager;
    }

    /**
     * Getter del gestor de NPC's
     * @return el gestor de NPC's
     */
    public NpcManager getNpcManager(){
        return npcManager;
    }

    /**
     * Setter del gestor de NPC's
     * @param npcManager el gestor de NPC's
     */
    public void setNpcManager(NpcManager npcManager){
        this.npcManager = npcManager;
    }

    /**
     * Este metodo chekeara que la partida este bien creada y cumpla con el enunciado
     * @param game el juego que queremos comprobar
     * @throws ErrorMessage el mensaje de error que queremos mostrar
     */
    //Comprueba que el juego tenga las cosas correctas y envia una excepcion si falla alguna cosa
    public void checkGame(Game game) throws ErrorMessage{

        if(gameDAO.gameNameExists(game.getGameName())){
            throw new ErrorMessage("El nom del game ya existeix.");
        } else if (!chechImp(game).equals("Correcte")) {
            throw  new ErrorMessage(chechImp(game));
        } else if(game.getGameName().isEmpty() || game.getGameName().equals(" ")){
            throw new ErrorMessage("El nom del joc no pot estar buit");
        }

    }

    /**
     * Setter del nombre de las partidas
     * @param gameName el nombre de las partidas
     */
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /**
     * Getter de las partidas asignadas a un usuario
     * @param user el usuario que queremos comprobar
     * @return la lista de los nombres de las partidas
     */
    public ArrayList<String> getGames(String user){
        ArrayList<String> games;
        games = gameDAO.getGames(user);
        return games;
    }

    /**
     * Este metodo servira para buscar una partida por su nombre
     * @param game el nombre de la partida
     * @return la partida en si
     */
    public Game searchGame(String game){
        return gameDAO.searchGame(game);
    }

    /**
     * Este metodo sirve para eliminar una partida especificada por su nombre
     * @param game el juego que queremos eliminar
     */
    public void deleteGame(String game){

        gameDAO.deleteGame(game);
    }

    /**
     * Este metodo nos permite guardar una partida en la base de datos
     * @param game el juego que queremos guardar
     */
    //Llama la base de datos para guardar los datos del juego
    public void saveGame(Game game){
        gameDAO.saveGame(game);
    }


    /**
     * Este metodo sirve para comprobar que el numero de impostores de una partida es correcto
     * @param game el juego que queremos comprobar
     * @return Una string que se mostrara si es correcto o si hay algun error
     */
    //Comprueba las diferentes condiciones de los impostores y nos devuelve el mensaje de error o correcte segun esta bien o mal
    private String chechImp(Game game) {
        //si <6 impos = 1 si entre 6 i 8 impos 2 si 9 o 10
        if(game.getPlayers() < 6 && game.getImpostors() > 1){
            return "No pot haber-hi mes de 1 impostor amb "+ game.getPlayers()+" jugadors";
        }else if (game.getPlayers() < 9 && game.getImpostors() > 2) {
            return "No pot haber-hi mes de 2 impostors amb "+ game.getPlayers()+" jugadors";
        }else{
        return "Correcte";
        }
    }

    /**
     * Getter de la casilla de cafeteria
     * @param cells las casillas
     * @return el nombre de la cafeteria
     */
    public Cell getCafeCell(LinkedList<Cell> cells) {
        for (Cell cell: cells) {
            if (cell.getRoomName().equals("cafeteria")) {
                return cell;
            }
        }
        return null;
    }

    /**
     * Este metodo nos dara el siguiente color disponible para los personajes
     * @param userColor el color del usuario
     * @param starterColor el color inicial
     * @param colors el listado de colores
     * @return el color que este disponible
     */
    public String getNextColor(String userColor, int starterColor, ArrayList<String> colors) {
        for (int i = starterColor; i < colors.size(); i++) {
            if (!colors.get(i).equals(userColor)) {
                return colors.get(i);
            }
        }
        return null;
    }

    /**
     * Getter de los NPC's
     * @param NpcNum El numero de NPC's de la partida
     * @param userColor el color del usuario
     * @param starterColor el color inicial
     * @param colors el listado de colores de los que disponemos
     * @param mapManager el gestor de mapas
     * @return El listado de los personajes de una partida
     */
    public LinkedList<Character> getNpcs(int NpcNum, String userColor, int starterColor, ArrayList<String> colors, MapManager mapManager) {
        LinkedList<Character> npcs = new LinkedList<>();
        for (int i = 0; i < NpcNum; i++) {
            Npc npc = new Npc(getNextColor(userColor, starterColor, colors), mapManager);
            starterColor++;
            if (colors.get(starterColor).equals(userColor)) {
                starterColor++;
            }
            npcs.add(npc);
        }
        return npcs;
    }

    /**
     * Getter de los impostores
     * @param impostorsNum numero de impostores
     * @param userColor color del usuario
     * @param starterColor color inicial
     * @param colors listado de los colores disponibles
     * @param mapManager el gestor de mapas
     * @return Listado de impostores de una partida
     */
    public LinkedList<Character> getImpostors(int impostorsNum, String userColor, int starterColor, ArrayList<String> colors, MapManager mapManager) {
        LinkedList<Character> impostors = new LinkedList<>();
        for (int i = 0; i < impostorsNum; i++) {
            Impostor impostor = new Impostor(getNextColor(userColor, starterColor, colors), mapManager);
            starterColor++;
            if (colors.get(starterColor).equals(userColor)) {
                starterColor++;
            }
            impostors.add(impostor);
        }
        return impostors;
    }

    /**
     * Setter de la casilla inicial del mapa
     * @param player el jugador del usuario
     * @param players listado de los NPC's e Impostores
     * @param cells las casillas del mapa
     */
    public void setInitialCell(Character player, LinkedList<Character> players, LinkedList<Cell> cells) {
        Cell initialCell = getCafeCell(cells);
        player.setCell(initialCell);
        for (Character character: players) {
            character.setCell(initialCell);
        }
    }

    /**
     * Metodo para iniciar el thread de los personajes
     * @param character los personajes de la partida
     */
    public void startPlayers(Character character) {
        character.startThread();
    }

    /**
     *
     * @param userColor el color del usuario
     * @param colors los colores de los que disponemos
     * @return
     */
    public int getUserColorPosition(String userColor, ArrayList<String> colors) {
        for (int i = 0; i < colors.size(); i++) {
            if (colors.get(i).equals(userColor)) {
                return i;
            }
        }
        return 0;
    }


    //#nuevo
    /**
     * Chekear si los impostores han ganado
     * @return un booleano para saber si el usuario ha ganado
     */
    public boolean checkImpostorsWin() {
        int crewMembersAlive = npcManager.getNumNpc();
        int numImpostors = npcManager.getNumImpostors();

        return crewMembersAlive + 1 <= numImpostors;
    }

    //#nuevo

    /**
     * Metodo para pausar los thread activos de los personajes
     */
    public void interruptThreads(){
        npcManager.interruptThreads();
    }

    //#nuevo
    /**
     * Aqui funcion que mira el numero de partidas ganadas el numero de victorias
     */
    public void setStatistics(){
        float ratio = ((float) (userManager.getnumWins(userManager.getUser())) /(userManager.getNumGames(userManager.getUser())))*100;
        statisticsManager.setGameStatistic(userManager.getUser(),gameName,ratio);
    }
    //#nuevo
    /**
     * Juego si la partida ha finalizado
     * @param win booleano para ver si se ha ganado la partida
     */
    public void finishGame(boolean win){
        userManager.gameFinish(win);

    }

    //#nuevo

    /**
     * Metodo par crear una nueva partida
     * @param name Este es el nombre de la partida
     * @param players entero con los jugadores que se pueden tener en esta partida
     * @param imp numero de impostores
     * @param color el color del personaje del usuario
     * @param mapName Nombre del mapa
     * @param user nombre del ususario que creo la partida
     * @return La partida que acabamos de crear
     */
    public Game newGame(String name,int players, int imp,String color,String mapName,String user){
        return new Game(name, players, imp, color, mapName,user);
    }




}
