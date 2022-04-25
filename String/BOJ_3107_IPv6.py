s = input()

splits = s.split(":")

for i in range(len(splits)):
  group = splits[i]
  
  if (group == '') and (i != 0) and i != (len(splits) - 1): 
    continue

  zero_cnt = 4 - len(group)
  splits[i] = ("0" * zero_cnt) + group

ans = ""

for i in range(len(splits)):
  if (splits[i] == ''):
    cnt = 8 - len(splits) + 1
    ans += "0000:" * cnt
  else:
    ans += splits[i]+":"

print(ans[:-1])