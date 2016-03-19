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

	
	public boolean run(Player caster)
	{
		final Location center = caster.getEyeLocation();
		final FireworkEffect.Builder builder = FireworkEffect.builder();
		final Location start1 = new Location(center.getWorld(),center.getX()+7,center.getY()+15,center.getZ());
		final Location start2 = start1.clone();
		try
		{
			final FireworkEffect fe = builder.flicker(true).with(Type.BURST).withColor(Color.AQUA).trail(false).build();
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
            center.getWorld().strikeLightningEffect(le.getLocation());
            le.setHealth(le.getHealth() - (le.getHealth()/10));
            le.damage(0f);
        }
	}
	
	public String getName() {
		return "Lightning Storm";
	}
	
}
