/**
 * ==========================================================================================
 * =                   JAHIA'S DUAL LICENSING - IMPORTANT INFORMATION                       =
 * ==========================================================================================
 *
 *                                 http://www.jahia.com
 *
 *     Copyright (C) 2002-2016 Jahia Solutions Group SA. All rights reserved.
 *
 *     THIS FILE IS AVAILABLE UNDER TWO DIFFERENT LICENSES:
 *     1/GPL OR 2/JSEL
 *
 *     1/ GPL
 *     ==================================================================================
 *
 *     IF YOU DECIDE TO CHOOSE THE GPL LICENSE, YOU MUST COMPLY WITH THE FOLLOWING TERMS:
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 *
 *     2/ JSEL - Commercial and Supported Versions of the program
 *     ===================================================================================
 *
 *     IF YOU DECIDE TO CHOOSE THE JSEL LICENSE, YOU MUST COMPLY WITH THE FOLLOWING TERMS:
 *
 *     Alternatively, commercial and supported versions of the program - also known as
 *     Enterprise Distributions - must be used in accordance with the terms and conditions
 *     contained in a separate written agreement between you and Jahia Solutions Group SA.
 *
 *     If you are unsure which license is appropriate for your use,
 *     please contact the sales department at sales@jahia.com.
 */
package org.jahia.modules.formbuilder.initializers;

import org.jahia.bin.Action;
import org.jahia.services.content.nodetypes.ExtendedPropertyDefinition;
import org.jahia.services.content.nodetypes.ValueImpl;
import org.jahia.services.content.nodetypes.initializers.ChoiceListValue;
import org.jahia.services.content.nodetypes.initializers.ModuleChoiceListInitializer;
import org.jahia.services.templates.JahiaTemplateManagerService;

import javax.jcr.PropertyType;
import java.util.*;

/**
 * @author rincevent
 * @since JAHIA 6.5
 * Created : 9 mars 2010
 */
public class ChoiceListFormBuilderActionInitializers implements ModuleChoiceListInitializer {
    private String key;
    private Map<String, String> actionsMap;
    private JahiaTemplateManagerService templateService;

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public Map<String, String> getActionsMap() {
        return actionsMap;
    }

    public void setActionsMap(Map<String, String> actionsMap) {
        this.actionsMap = actionsMap;
    }

    public void setTemplateService(JahiaTemplateManagerService templateService) {
        this.templateService = templateService;
    }

    public List<ChoiceListValue> getChoiceListValues(ExtendedPropertyDefinition epd, String param, List<ChoiceListValue> values, Locale locale,
                                                     Map<String, Object> context) {
        Map<String, Action> map = templateService.getActions();
        List<ChoiceListValue> vs = new ArrayList<ChoiceListValue>();
        for (Map.Entry<String, String> action : actionsMap.entrySet()) {
            if (map.containsKey(action.getValue()) || action.getKey().equals("default")) {
                vs.add(new ChoiceListValue(action.getKey(), new HashMap<String, Object>(), new ValueImpl(
                        action.getValue(), PropertyType.STRING, false)));
            }
        }
        return vs;
    }
}
