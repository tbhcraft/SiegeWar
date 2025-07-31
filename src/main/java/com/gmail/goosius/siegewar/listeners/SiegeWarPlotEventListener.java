package com.gmail.goosius.siegewar.listeners;

import com.gmail.goosius.siegewar.SiegeWar;
import com.gmail.goosius.siegewar.objects.BattleSession;
import com.gmail.goosius.siegewar.settings.SiegeWarSettings;
import com.gmail.goosius.siegewar.utils.SiegeWarDistanceUtil;
import com.palmergames.bukkit.towny.event.damage.TownBlockPVPTestEvent;
import com.palmergames.bukkit.towny.event.damage.TownBlockExplosionTestEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SiegeWarPlotEventListener implements Listener {
    @SuppressWarnings("unused")
	private final SiegeWar plugin;
	
	public SiegeWarPlotEventListener(SiegeWar instance) {
		plugin = instance;
    }

	/**
	 * When a townblock in in a SiegeZone, PVP is always on
	 *
	 * @param event the test event
	 */
	@EventHandler(ignoreCancelled = true)
	public void onTownBlockPVPTest(TownBlockPVPTestEvent event) {
		if (SiegeWarSettings.getWarSiegeEnabled()
		    && event.getTownBlock().getWorld().isWarAllowed()
		    && SiegeWarSettings.isStopTownyPlotPvpProtection()
		    && BattleSession.getBattleSession().isActive()
		    && !event.getTownBlock().getPermissions().pvp
		    && SiegeWarDistanceUtil.isTownBlockInActiveSiegeZone(event.getTownBlock())) {
				event.setPvp(true);
		}
	}
	@EventHandler(ignoreCancelled = true)
	public void onTownBlockExplosionTest(TownBlockExplosionTestEvent event) {
		if (SiegeWarSettings.getWarSiegeEnabled()
		&& event.getTownBlock().getWorld().isWarAllowed()
		&& SiegeWarSettings.isStopTownyPlotExplosionProtection()
		&& BattleSession.getBattleSession().isActive()
		&& !event.getTownBlock().getPermissions().explosion
		&&SiegeWarDistanceUtil.isTownBlockInActiveSiegeZone((event.getTownBlock()))) {
			event.setExplosion(true);
		}
	}
}
