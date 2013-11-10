/*
 * This file is part of Technic Launcher.
 *
 * Copyright (c) 2013-2013, Technic <http://www.technicpack.net/>
 * Technic Launcher is licensed under the Spout License Version 1.
 *
 * Technic Launcher is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the Spout License Version 1.
 *
 * Technic Launcher is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the Spout License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */

package net.technicpack.legacywrapper;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.internal.Lists;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class StartupParameters {
    @SuppressWarnings("unused")
    private final String[] args;
    public StartupParameters(String[] args) {
        this.args = args;
        auth_player_name = args[0];
        auth_session = args[1];
    }
    @Parameter
    private List<String> parameters = Lists.newArrayList();

    private String auth_player_name;

    private String auth_session;

    @Parameter(names = {"--gameDir"}, description = "Pack directory")
    private String gameDir;

    @Parameter(names = {"--assetsDir"}, description = "Assets directory")
    private String assetsDir;

    @Parameter(names = {"--width"}, description = "Sets the width of the minecraft window to be fixed to this.")
    private int width = -1;

    @Parameter(names = {"--height"}, description = "Sets the height of the minecraft window to be fixed to this.")
    private int height = -1;

	@Parameter(names = {"--title"}, description = "The title bar text for the Minecraft frame.")
	private String title = "Minecraft";

	@Parameter(names = {"--icon"}, description = "The title bar icon for the Minecraft frame.")
	private String icon = null;

    public String getAuthPlayerName() { return auth_player_name; }
    public String getAuthSession() { return auth_session; }
    public String getGameDirectory() { return gameDir; }
    public String getAssetsDirectory() { return assetsDir; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
	public String getTitle() { return title; }
	public String getIconPath() { return icon; }
}