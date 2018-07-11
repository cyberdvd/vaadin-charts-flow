package com.vaadin.flow.component.charts.model;

/*-
 * #%L
 * Vaadin Charts for Flow
 * %%
 * Copyright (C) 2014 - 2018 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <https://vaadin.com/license/cval-3>.
 * #L%
 */

import javax.annotation.Generated;

/**
 * Options for the paging or navigation appearing when the legend is overflown.
 * Navigation works well on screen, but not in static exported images. One way
 * of working around that is to [increase the chart height in
 * export](http://jsfiddle
 * .net/gh/get/library/pure/highcharts/highcharts/tree/master
 * /samples/highcharts/legend/navigation- enabled-false/).
 */
@Generated(value = "This class is generated and shouldn't be modified", comments = "Incorrect and missing API should be reported to https://github.com/vaadin/vaadin-charts-flow/issues/new")
public class LegendNavigation extends AbstractConfigurationObject {

	private Boolean animation;
	private Number arrowSize;
	private Boolean enabled;

	public LegendNavigation() {
	}

	public LegendNavigation(Boolean enabled) {
		setEnabled(enabled);
	}

	/**
	 * @see #setAnimation(Boolean)
	 */
	public Boolean getAnimation() {
		return animation;
	}

	/**
	 * How to animate the pages when navigating up or down. A value of `true`
	 * applies the default navigation given in the chart.animation option.
	 * Additional options can be given as an object containing values for easing
	 * and duration.
	 */
	public void setAnimation(Boolean animation) {
		this.animation = animation;
	}

	/**
	 * @see #setArrowSize(Number)
	 */
	public Number getArrowSize() {
		return arrowSize;
	}

	/**
	 * The pixel size of the up and down arrows in the legend paging navigation.
	 */
	public void setArrowSize(Number arrowSize) {
		this.arrowSize = arrowSize;
	}

	/**
	 * @see #setEnabled(Boolean)
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * Whether to enable the legend navigation. In most cases, disabling the
	 * navigation results in an unwanted overflow. See also the [adapt chart to
	 * legend](http://www.highcharts.com/plugin-
	 * registry/single/8/Adapt-Chart-To-Legend) plugin for a solution to extend
	 * the chart height to make room for the legend, optionally in exported
	 * charts only.
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
