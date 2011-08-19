package hudson.plugins.nextexecutions;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

import org.kohsuke.stapler.Stapler;

import antlr.ANTLRException;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.model.Hudson;
import hudson.model.View;
import hudson.model.TopLevelItem;
import hudson.plugins.nextexecutions.utils.NextExecutionsUtils;
import hudson.scheduler.CronTab;
import hudson.triggers.TimerTrigger;
import hudson.triggers.Trigger;
import hudson.widgets.Widget;

@Extension
public class NextExecutionsWidget extends Widget {
	 private static final Logger LOGGER = Logger.getLogger(NextExecutionsWidget.class.getName());
	 
	public List<NextBuilds> getBuilds() {		
		List<NextBuilds> nblist = new Vector<NextBuilds>();

		List<AbstractProject>	l = Hudson.getInstance().getItems(AbstractProject.class); 
			
		for (AbstractProject project: l) {
			NextBuilds nb = NextExecutionsUtils.getNextBuild(project);
			if(nb != null)
				nblist.add(nb);
		}
		Collections.sort(nblist);
		return nblist;
	}
}
