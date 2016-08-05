package code.op.skill;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import code.op.Main;
import code.op.utils.CustomEntityFirework;

public class LightningStorm extends Special {

	private final FireworkEffect.Builder builder = FireworkEffect.builder();
	private final FireworkEffect fe = builder.flicker(true).with(Type.BURST).withColor(Color.AQUA).trail(false).build();
	
	public boolean run(Player caster)
	{
		final Location center = caster.getEyeLocation();
		final Location start1 = new Location(center.getWorld(),center.getX()+7,center.getY()+15,center.getZ());
		final Location start2 = start1.clone();
		try
		{
			CustomEntityFirework.spawn(start1, fe);
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						CustomEntityFirework.spawn(start1.add(0, 0, 1), fe);
						CustomEntityFirework.spawn(start2.add(0, 0, -1), fe);
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
						{
							@Override
							public void run()
							{
								try
								{
									CustomEntityFirework.spawn(start1.add(0, 0, 1), fe);
									CustomEntityFirework.spawn(start2.add(0, 0, -1), fe);
									Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
									{
										@Override
										public void run()
										{
											try
											{
												CustomEntityFirework.spawn(start1.add(-1, 0, 1), fe);
												CustomEntityFirework.spawn(start2.add(-1, 0, -1), fe);
												Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
												{
													@Override
													public void run()
													{
														try
														{
															CustomEntityFirework.spawn(start1.add(0, 0, 1), fe);
															CustomEntityFirework.spawn(start2.add(0, 0, -1), fe);
															Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
															{
																@Override
																public void run()
																{
																	try
																	{
																		CustomEntityFirework.spawn(start1.add(-1, 0, 1), fe);
																		CustomEntityFirework.spawn(start2.add(-1, 0, -1), fe);
																		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
																		{
																			@Override
																			public void run()
																			{
																				try
																				{
																					CustomEntityFirework.spawn(start1.add(-1, 0, 1), fe);
																					CustomEntityFirework.spawn(start2.add(-1, 0, -1), fe);
																					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
																					{
																						@Override
																						public void run()
																						{
																							try
																							{
																								CustomEntityFirework.spawn(start1.add(-1, 0, 0), fe);
																								CustomEntityFirework.spawn(start2.add(-1, 0, 0), fe);
																								Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
																								{
																									@Override
																									public void run()
																									{
																										try
																										{
																											CustomEntityFirework.spawn(start1.add(-1, 0, 1), fe);
																											CustomEntityFirework.spawn(start2.add(-1, 0, -1), fe);
																											Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
																											{
																												@Override
																												public void run()
																												{
																													try
																													{
																														CustomEntityFirework.spawn(start2.add(-1, 0, 0), fe);
																														CustomEntityFirework.spawn(start2.add(-1, 0, 0), fe);
																														Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
																														{
																															@Override
																															public void run()
																															{
																																try
																																{
																																	CustomEntityFirework.spawn(start1.add(-1, 0, 0), fe);
																																	CustomEntityFirework.spawn(start2.add(-1, 0, 0), fe);
																																	Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
																																	{
																																		@Override
																																		public void run()
																																		{
																																			try
																																			{
																																				CustomEntityFirework.spawn(start1.add(-1, 0, 0), fe);
																																				CustomEntityFirework.spawn(start2.add(-1, 0, 0), fe);
																																				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
																																				{
																																					@Override
																																					public void run()
																																					{
																																						try
																																						{
																																							CustomEntityFirework.spawn(start1.add(-1, 0, 0), fe);
																																							CustomEntityFirework.spawn(start2.add(-1, 0, 0), fe);
																																							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
																																							{
																																								@Override
																																								public void run()
																																								{
																																									try
																																									{
																																										CustomEntityFirework.spawn(start1.add(-1, 0, -1), fe);
																																										CustomEntityFirework.spawn(start2.add(-1, 0, 1), fe);
																																										Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
																																										{
																																											@Override
																																											public void run()
																																											{
																																												try
																																												{
																																													CustomEntityFirework.spawn(start1.add(-1, 0, 0), fe);
																																													CustomEntityFirework.spawn(start2.add(-1, 0, 0), fe);
																																													Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
																																													{
																																														@Override
																																														public void run()
																																														{
																																															try
																																															{
																																																CustomEntityFirework.spawn(start1.add(-1, 0, -1), fe);
																																																CustomEntityFirework.spawn(start2.add(-1, 0, 1), fe);
																																																Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
																																																{
																																																	@Override
																																																	public void run()
																																																	{
																																																		try
																																																		{
																																																			CustomEntityFirework.spawn(start1.add(-1, 0, -1), fe);
																																																			CustomEntityFirework.spawn(start2.add(-1, 0, 1), fe);
																																																			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
																																																			{
																																																				@Override
																																																				public void run()
																																																				{
																																																					try
																																																					{
																																																						CustomEntityFirework.spawn(start1.add(0, 0, -1), fe);
																																																						CustomEntityFirework.spawn(start2.add(0, 0, 1), fe);
																																																						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
																																																						{
																																																							@Override
																																																							public void run()
																																																							{
																																																								try
																																																								{
																																																									CustomEntityFirework.spawn(start1.add(-1, 0, -1), fe);
																																																									CustomEntityFirework.spawn(start2.add(-1, 0, 1), fe);
																																																									Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
																																																									{
																																																										@Override
																																																										public void run()
																																																										{
																																																											try
																																																											{
																																																												CustomEntityFirework.spawn(start1.add(0, 0, -1), fe);																																																										
																																																												CustomEntityFirework.spawn(start2.add(0, 0, 1), fe);
																																																												Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
																																																												{
																																																													@Override
																																																													public void run()
																																																													{
																																																														try
																																																														{
																																																															CustomEntityFirework.spawn(start1.add(0, 0, -1), fe);
																																																															CustomEntityFirework.spawn(start2.add(0, 0, 1), fe);
																																																															Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
																																																															{
																																																																@Override
																																																																public void run()
																																																																{
																																																																	strikeLigtning(center,15,7,caster);
																																																																}
																																																															},(long)1.65);
																																																														}
																																																														catch (Exception e)
																																																														{
																																																															e.printStackTrace();
																																																														}
																																																													}
																																																												},(long)1.65);
																																																											}
																																																											catch (Exception e)
																																																											{
																																																												e.printStackTrace();
																																																											}
																																																										}
																																																									},(long)1.65);
																																																								}
																																																								catch (Exception e)
																																																								{
																																																									e.printStackTrace();
																																																								}
																																																							}
																																																						},(long)1.65);
																																																					}
																																																					catch (Exception e)
																																																					{
																																																						e.printStackTrace();
																																																					}
																																																				}
																																																			},(long)1.65);
																																																		}
																																																		catch (Exception e)
																																																		{
																																																			e.printStackTrace();
																																																		}
																																																	}
																																																},(long)1.65);
																																															}
																																															catch (Exception e)
																																															{
																																																e.printStackTrace();
																																															}
																																														}
																																													},(long)1.65);
																																												}
																																												catch (Exception e)
																																												{
																																													e.printStackTrace();
																																												}
																																											}
																																										},(long)1.65);
																																									}
																																									catch (Exception e)
																																									{
																																										e.printStackTrace();
																																									}
																																								}
																																							},(long)1.65);
																																						}
																																						catch (Exception e)
																																						{
																																							e.printStackTrace();
																																						}
																																					}
																																				},(long)1.65);
																																			}
																																			catch (Exception e)
																																			{
																																				e.printStackTrace();
																																			}
																																		}
																																	},(long)1.65);
																																}
																																catch (Exception e)
																																{
																																	e.printStackTrace();
																																}
																															}
																														},(long)1.65);
																													}
																													catch (Exception e)
																													{
																														e.printStackTrace();
																													}
																												}
																											},(long)1.65);
																										}
																										catch (Exception e)
																										{
																											e.printStackTrace();
																										}
																									}
																								},(long)1.65);
																							}
																							catch (Exception e)
																							{
																								e.printStackTrace();
																							}
																						}
																					},(long)1.65);
																				}
																				catch (Exception e)
																				{
																					e.printStackTrace();
																				}
																			}
																		},(long)1.65);
																	}
																	catch (Exception e)
																	{
																		e.printStackTrace();
																	}
																}
															},(long)1.65);
														}
														catch (Exception e)
														{
															e.printStackTrace();
														}
													}
												},(long)1.65);
											}
											catch (Exception e)
											{
												e.printStackTrace();
											}
										}
									},(long)1.65);
								}
								catch (Exception e)
								{
									e.printStackTrace();
								}
							}
						},(long)1.65);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			},(long)1.65);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
	
	public void strikeLigtning(Location center, int height, int radius, Player caster) {
        for(Entity en : center.getWorld().getEntities()) {
        	if(en == null)
        		continue;
        	if(!(en instanceof LivingEntity))
        		continue;
        	if(en instanceof Player) {
        		if(((Player)en).equals(caster))
        			continue;
        	}
        	LivingEntity le = (LivingEntity)en;
        	
        	Location loc = le.getLocation();
        	
        	if(center.getBlockX()+radius-loc.getBlockX() >= 0 && center.getBlockX()+radius-loc.getBlockX() < radius*2)
        	{
	        		if(center.getBlockZ()+radius-loc.getBlockZ() >= 0 && center.getBlockZ()+radius-loc.getBlockZ() < radius*2)
	            	{
	        			if(center.getBlockY()+height-loc.getBlockY() < center.getBlockY()+height)
	                	{
	                		if(lengthSq((center.getBlockX()+radius-loc.getBlockX() + 1)*7, (center.getBlockZ()+radius-loc.getBlockZ() + 1)*15) <= 1)
	                		{
	                			center.getWorld().strikeLightningEffect(le.getLocation());
	                			if(le instanceof Player)
	                				le.setHealth(le.getHealth()/2);
	                			else
	                				le.setHealth(le.getHealth()/2);
	                			le.setFireTicks(20*6);
	                			le.damage(0f);
	                		}
	                	}
	            	}
	        	}
        }
	}
	
	public String getName() {
		return "Lightning Storm";
	}
	
	private final double lengthSq(double x, double z)
    {
        return (x * x) + (z * z);
    }
	
}
