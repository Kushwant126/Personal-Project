package com.example.personalproject.mainPackage.dataobject;

import com.example.personalproject.mainPackage.common.MenuClass;

import java.util.Vector;

public class MenuDO 
{
	public String menuName;
	public int menuImage;
	public Vector<MenuDO> vecMenuDOs = new Vector<MenuDO>();
	public MenuClass.MENUS objMenu;
}
