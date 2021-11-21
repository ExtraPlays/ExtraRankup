
# ExtraRankup

Crie varios Ranks de uma maneira pratica.

## Placeholders

PlaceHolderAPI

 - %erankup.rank% 
 - %erankup.next_rank% 
 - %erankup.next_rank_cost%

## Comandos e Permissões

| Comando | Permissão | Descrição |
|--|--|--|
| /erankup | erankup.admin | Comando admin |
| /rankup | erankup.menu| Abre o menu de RankUp |
| /rank | erankup.rank| Informações sobre o rank atual |

## Configuração

 ranks.yml

    ranks:
      ferro4: # identifier
        tag: "&7[&bFerro 4&7]&f" # tag
        cost: 0 # Cost to RankUP
        nextRank: "ferro3" # next rank identifier
        default: true # is default rank?
        material: PLAYER_HEAD # Material
        slot: 20 # Slot in menu
        lore: # Description
          - ""
          - "&fRank: @rank"
          - "&fPreço para subir de Rank: &6@cost"
          - ""
