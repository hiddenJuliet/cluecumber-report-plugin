/*
 * Copyright 2018 trivago N.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.trivago.rta.rendering.pages.pojos;

import com.trivago.rta.constants.PluginSettings;
import com.trivago.rta.rendering.RenderingUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportDetails {
    private String chartJson;
    private String date;
    private String pageName;

    public ReportDetails(final String pageName) {
        this.pageName = pageName;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        this.date = dateFormat.format(date);
    }

    public String getChartJson() {
        return chartJson;
    }

    public void setChartJson(final String chartJson) {
        this.chartJson = chartJson;
    }

    public String getDate() {
        return date;
    }

    public String getGeneratorName() {
        return String.format("%s [%s]",
                PluginSettings.NAME, RenderingUtils.getPluginVersion());
    }

    public String getPageName() {
        return pageName;
    }
}
