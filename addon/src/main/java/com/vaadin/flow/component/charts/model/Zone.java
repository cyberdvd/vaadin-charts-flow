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
import com.vaadin.flow.component.charts.model.style.Color;

@Generated(value = "This class is generated and shouldn't be modified", comments = "Incorrect and missing API should be reported to https://github.com/vaadin/vaadin-charts-flow/issues/new")
public class Zone extends AbstractConfigurationObject {

	private String className;
	private Color color;
	private String dashStyle;
	private Color fillColor;
	private Number value;

	public Zone() {
	}

	/**
	 * @see #setClassName(String)
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * Styled mode only. A custom class name for the zone.
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @see #setColor(Color)
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Defines the color of the series.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @see #setDashStyle(String)
	 */
	public String getDashStyle() {
		return dashStyle;
	}

	/**
	 * A name for the dash style to use for the graph.
	 */
	public void setDashStyle(String dashStyle) {
		this.dashStyle = dashStyle;
	}

	/**
	 * @see #setFillColor(Color)
	 */
	public Color getFillColor() {
		return fillColor;
	}

	/**
	 * Defines the fill color for the series (in area type series)
	 */
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	/**
	 * @see #setValue(Number)
	 */
	public Number getValue() {
		return value;
	}

	/**
	 * The value up to where the zone extends, if undefined the zones stretches
	 * to the last value in the series.
	 */
	public void setValue(Number value) {
		this.value = value;
	}
}