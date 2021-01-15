package League;

public interface Items {
public void giveStats(Unit champ);
public void sell(Unit champ);
public void putInventory(Unit champ);
public boolean buildsInto(Items item);
}
