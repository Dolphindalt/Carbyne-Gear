package code.op.skill;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import code.op.Main;
import code.op.utils.CustomEntityFirework;

public class ShadowSweep extends Special {

	private final PotionEffect pot = new PotionEffect(PotionEffectType.BLINDNESS, 80, 1);
	
	public boolean run(Player caster)
	{
		final Location center = caster.getEyeLocation();
		final FireworkEffect.Builder builder = FireworkEffect.builder();
		final Location start1 = new Location(center.getWorld(),center.getX()+7,center.getY(),center.getZ());
		final Location start2 = start1.clone();
		try
		{
			final FireworkEffect fe = builder.flicker(true).with(Type.STAR).withColor(Color.BLACK).withColor(Color.RED).trail(false).build();
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
																																																																	damage(center,15,7,caster);
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
	
	public void damage(Location center, int height, int radius, Player caster) {
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
	                				le.setHealth(le.getHealth()/4);
	                			else
	                				le.setHealth(le.getHealth()/4);
	                			le.setFireTicks(20*6);
	                            le.addPotionEffect(pot);
	                			le.damage(0f);
	                		}
	                	}
	            	}
	        	}
        }
	}
	
	public String getName() {
		return "ShadowSweep";
	}
	
	private final double lengthSq(double x, double z)
    {
        return (x * x) + (z * z);
    }
	
}
